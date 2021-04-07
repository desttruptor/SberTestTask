package me.podlesnykh.sbertesttask.currency_list_fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.databinding.CurrencyListItemBinding
import me.podlesnykh.sbertesttask.network.pojo.Valute
import java.text.NumberFormat
import java.util.*

class CurrencyListAdapter(
    private var currency: List<Valute>,
    private val onClick: (Double, Int, String) -> Unit
) : RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.currency_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) =
        holder.bind(currency[position])

    override fun getItemCount() = currency.size

    fun submitList(newList: List<Valute>) {
        // вычисление разницы между списком, который держит адаптер и новым списком
        val diffResult = DiffUtil.calculateDiff(CurrencyListDiffUtilsCallback(currency, newList))
        // замена списка, который держит адаптер на новый
        currency = newList
        diffResult.dispatchUpdatesTo(this)
    }

    // из-за записи value c запятой нужна коррекция формата
    private fun doubleFromString(s: String) : Double{
        val format = NumberFormat.getInstance(Locale.getDefault())
        val num = format.parse(s)
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return num.toDouble()
    }

    inner class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val listItemBinding = CurrencyListItemBinding.bind(itemView)

        fun bind(valute: Valute) {
            val nameAndNominal = valute.nominal.toString() + " " + valute.name
            listItemBinding.currencyName.text = nameAndNominal
            listItemBinding.currencyValue.text = valute.value
            listItemBinding.currencyShortName.text = valute.charCode
            listItemBinding.root.setOnClickListener {
                onClick(
                    doubleFromString(valute.value),
                    valute.nominal,
                    valute.name
                )
            }
        }
    }

    inner class CurrencyListDiffUtilsCallback(
        private val oldList: List<Valute>,
        private val newList: List<Valute>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].numCode == newList[newItemPosition].numCode

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}
