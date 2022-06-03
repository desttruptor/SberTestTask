package me.podlesnykh.sbertesttask.currency_converter_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class CurrencyConverterViewModel(init_value: Double, init_nominal: Double) : ViewModel() {
    private val _mutableValue = MutableLiveData<Double>()
    val value get() = _mutableValue

    private val _mutableNominal = MutableLiveData<Double>()
    val nominal get() = _mutableNominal

    private val factor by lazy {
        value.value?.div(nominal.value ?: 1.0) ?: 1.0
    }

    init {
        _mutableValue.value = init_value
        _mutableNominal.value = init_nominal
    }

    fun calculateValue(nominal: Double) {
        val value = ((nominal * factor) * 100.0).roundToInt() / 100.0
        _mutableValue.value = value
    }

    fun calculateNominal(value: Double) {
        val nominal = ((value / factor) * 100.0).roundToInt() / 100.0
        _mutableNominal.value = nominal
    }

    fun saveValueAndNominal(value: Double, nominal: Double) {
        _mutableValue.value = value
        _mutableNominal.value = nominal
    }
}