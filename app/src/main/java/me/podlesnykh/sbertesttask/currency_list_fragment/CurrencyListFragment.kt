package me.podlesnykh.sbertesttask.currency_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.currency_list_fragment.adapters.CurrencyListAdapter
import me.podlesnykh.sbertesttask.databinding.FragmentCurrencyListBinding
import me.podlesnykh.sbertesttask.network.CurrencyItem

class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    // адаптер инициализируется пустым списком
    private val currencyList: List<CurrencyItem> = listOf(
        CurrencyItem(
            numCode = 100,
            charCode = "CAD",
            nominal = 1,
            name = "Канадский доллар",
            value = 60.9817
        )
    )
    private val adapter = CurrencyListAdapter(currencyList, ::openConverterScreen)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.label_currency_rate)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // переход на экран конвертера выбранной валюты
    private fun openConverterScreen(value: Double, nominal: Int) {
        findNavController().navigate(
            R.id.currencyConverterFragment,
            bundleOf(
                "value" to value,
                "nominal" to nominal
            )
        )
    }

    private fun setupRecyclerView() {
        binding.rvCurrencyList.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        binding.rvCurrencyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}