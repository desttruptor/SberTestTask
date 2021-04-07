package me.podlesnykh.sbertesttask.currency_list_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrencyListFragmentViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(CurrencyListFragmentViewModel::class.java)) {
            return CurrencyListFragmentViewModel(application) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}