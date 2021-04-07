package me.podlesnykh.sbertesttask.network

import me.podlesnykh.sbertesttask.BuildConfig
import okhttp3.*
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader

class CurrencyRateRepository() {
    companion object {
        val client = OkHttpClient()
    }

    private fun loadCurrencyList(): Any {
        val request = Request.Builder()
            .url(BuildConfig.CURRENCY_RATE_URL)
            .build()

        lateinit var callResult: Any

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callResult = Result.Error(e.message ?: "")
            }

            override fun onResponse(call: Call, response: Response) {
                val reader = StringReader(response.body.toString())
                val serializer = Persister()
                val currencyList = serializer.read(CurrencyList::class.java, reader, false)
                callResult = Result.Success(currencyList)
            }
        })

        return callResult
    }
}