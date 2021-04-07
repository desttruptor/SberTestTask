package me.podlesnykh.sbertesttask.currency_list_fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.databinding.CurrencyListItemBinding
import me.podlesnykh.sbertesttask.network.CurrencyItem

class CurrencyListAdapter(
    private var currency: List<CurrencyItem>,
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

    fun submitList(newList: List<CurrencyItem>) {
        // вычисление разницы между списком, который держит адаптер и новым списком
        val diffResult = DiffUtil.calculateDiff(CurrencyListDiffUtilsCallback(currency, newList))
        // замена списка, который держит адаптер на новый
        currency = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val listItemBinding = CurrencyListItemBinding.bind(itemView)

        fun bind(currencyItem: CurrencyItem) {
            val nameAndNominal = currencyItem.nominal.toString() + " " + currencyItem.name
            listItemBinding.currencyName.text = nameAndNominal
            listItemBinding.currencyValue.text = currencyItem.value.toString()
            listItemBinding.currencyShortName.text = currencyItem.charCode
            listItemBinding.root.setOnClickListener {
                onClick(
                    currencyItem.value,
                    currencyItem.nominal,
                    currencyItem.name
                )
            }
        }
    }

    inner class CurrencyListDiffUtilsCallback(
        private val oldList: List<CurrencyItem>,
        private val newList: List<CurrencyItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].numCode == newList[newItemPosition].numCode

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}
