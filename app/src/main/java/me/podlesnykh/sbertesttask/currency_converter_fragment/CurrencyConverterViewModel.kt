package me.podlesnykh.sbertesttask.currency_converter_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrencyConverterViewModel(init_value: Double, init_nominal: Double) : ViewModel() {
    private val _mutableValue = MutableLiveData<Double>()
    val value get() = _mutableValue

    private val _mutableNominal = MutableLiveData<Double>()
    val nominal get() = _mutableNominal

    private val factor by lazy {
        value.value?.div(nominal.value ?: 1.0) ?: 1.0
    }

    init {
        updateValue(init_value)
        updateNominal(init_nominal)
    }

    fun updateValue(value: Double) {
        _mutableValue.value = value
    }

    fun updateNominal(nominal: Double) {
        _mutableNominal.value = nominal
    }

    fun calculateValue(nominal: Double) {
        val value = Math.round((nominal * factor) * 100.0) / 100.0
        updateValue(value)
    }

    fun calculateNominal(value: Double) {
        val nominal = Math.round((value / factor) * 100.0) / 100.0
        updateNominal(nominal)
    }
}