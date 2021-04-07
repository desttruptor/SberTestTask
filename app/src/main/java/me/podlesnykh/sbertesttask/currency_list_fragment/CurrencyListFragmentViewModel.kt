package me.podlesnykh.sbertesttask.currency_list_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.podlesnykh.sbertesttask.BuildConfig
import me.podlesnykh.sbertesttask.network.pojo.CurrencyList
import me.podlesnykh.sbertesttask.network.CurrencyRateRepository
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader

class CurrencyListFragmentViewModel : ViewModel() {

    private val _currencyList = MutableLiveData<CurrencyList>()
    val currencyList get() = _currencyList

    private val _loadingFlag = MutableLiveData<Boolean>()
    val loadingFlag get() = _loadingFlag

    private val _errorDialog = MutableLiveData<Boolean>()
    val errorDialog get() = _errorDialog

    fun loadCurrencyList() {
        val request = Request.Builder()
            .url(BuildConfig.CURRENCY_RATE_URL)
            .build()


        CurrencyRateRepository.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorDialog.postValue(true)
            }

            override fun onResponse(call: Call, response: Response) {
                _currencyList.postValue(
                    parseXml(response)
                )
            }
        })
    }

    private fun parseXml(response: Response): CurrencyList {
        val reader = StringReader(response.body?.string())
        val serializer = Persister()
        return serializer.read(CurrencyList::class.java, reader, false)
    }
}