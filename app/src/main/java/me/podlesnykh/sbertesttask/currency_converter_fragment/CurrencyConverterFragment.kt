package me.podlesnykh.sbertesttask.currency_converter_fragment
//терминология: value - верхнее поле для ввода
//nominal - нижнее поле для ввода

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.databinding.FragmentConverterBinding

class CurrencyConverterFragment : Fragment() {

    private lateinit var viewModel: CurrencyConverterViewModel

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
        // установить текст с именем второй валюты
        binding.labelCurrencyCustom.text = requireArguments().getString("name")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, CurrencyConverterViewModelFactory(
                requireArguments().getDouble("value"),
                requireArguments().getInt("nominal").toDouble()
            )
        ).get(CurrencyConverterViewModel::class.java)

        viewModel.value.observe(viewLifecycleOwner) {
            binding.currency1.setText(
                it.toString(),
                TextView.BufferType.EDITABLE
            )
        }

        viewModel.nominal.observe(viewLifecycleOwner) {
            binding.currency2.setText(
                it.toString(),
                TextView.BufferType.EDITABLE
            )
        }

        // расчет нижнего из верхнего
        binding.calculateValue.setOnClickListener {
            viewModel.calculateNominal(
                binding.currency1.text.toString().toDouble()
            )
        }

        // расчет верхнего из нижнего
        binding.calculateNominal.setOnClickListener {
            viewModel.calculateValue(
                binding.currency2.text.toString().toDouble()
            )
        }
    }

    // для того, чтобы при перевороте экрана не терялись значения в полях ввода
    override fun onStop() {
        super.onStop()
        viewModel.saveValueAndNominal(
            binding.currency1.text.toString().toDouble(),
            binding.currency2.text.toString().toDouble()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}