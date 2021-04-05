package me.podlesnykh.sbertesttask.currency_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import me.podlesnykh.sbertesttask.currency_converter_fragment.CurrencyConverterFragment
import me.podlesnykh.sbertesttask.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openConverterScreen(numCode: Int) {
        findNavController().navigate(
            CurrencyListFragmentDirections.navigateToConverter(numCode)
        )
    }
}