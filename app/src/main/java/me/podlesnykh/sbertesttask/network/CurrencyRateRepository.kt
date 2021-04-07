package me.podlesnykh.sbertesttask.network

import android.util.Log
import me.podlesnykh.sbertesttask.BuildConfig
import org.simpleframework.xml.core.Persister
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable

class CurrencyRateRepository() {
    fun loadCurrencyList(): Any {
        return Callable<Any> {
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(BuildConfig.CURRENCY_RATE_URL)
                urlConnection = url.openConnection() as HttpURLConnection

                val code = urlConnection.responseCode
                if (code != 200) {
                    return@Callable Result.Error("Invalid response from server $code")
                }

                val rd = BufferedReader(InputStreamReader(urlConnection.inputStream)).toString()
                val reader = StringReader(rd)
                val serializer = Persister()

                try {
                    val currencyList = serializer.read(CurrencyList::class.java, reader, false)
                    return@Callable Result.Success(currencyList)
                } catch (e: Exception) {
                    Log.e("Xml parser", e.message.toString())
                }
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect()
                }

            }
        }
    }
}