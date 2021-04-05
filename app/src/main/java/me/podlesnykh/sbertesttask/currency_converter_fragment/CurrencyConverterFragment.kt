package me.podlesnykh.sbertesttask.currency_converter_fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.databinding.FragmentConverterBinding

class CurrencyConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.label_currency_converter)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}