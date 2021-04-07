package me.podlesnykh.sbertesttask.currency_converter_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CurrencyConverterViewModelFactory(
    private val init_value: Double,
    private val init_nominal: Double
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyConverterViewModel::class.java)) {
            return CurrencyConverterViewModel(init_value, init_nominal) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}