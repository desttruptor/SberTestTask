package me.podlesnykh.sbertesttask.currency_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.podlesnykh.sbertesttask.databinding.FragmentCurrencyListBinding
import me.podlesnykh.sbertesttask.network.CurrencyItem

class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    // адаптер инициализируется пустым списком
    private val currencyList: List<CurrencyItem> = emptyList()
    private val adapter = CurrencyListAdapter(currencyList, this::openConverterScreen)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val view = binding.root
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
    private fun openConverterScreen(numCode: Int) {
        findNavController().navigate(
            CurrencyListFragmentDirections.navigateToConverter(numCode)
        )
    }

    private fun setupRecyclerView() {
        binding.rvCurrencyList.adapter = adapter
    }
}