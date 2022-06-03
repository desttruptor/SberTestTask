package me.podlesnykh.sbertesttask.network

import android.util.Log
import okhttp3.OkHttpClient

object OkHttpInstance {
    private const val TAG_REQUEST_LOG = "Request log: "
    private const val TAG_RESPONSE_LOG = "Response log: "

    val client = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        Log.v(TAG_REQUEST_LOG, "Sending request to ${request.url}")
        val response = chain.proceed(request)
        Log.v(TAG_RESPONSE_LOG, "Received response body:\n${response.body}")

        return@addInterceptor response
    }.build()
}