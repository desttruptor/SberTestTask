package me.podlesnykh.sbertesttask

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CurrencyRateApplication : Application() {
    val executorService: ExecutorService = Executors.newFixedThreadPool(4)
}