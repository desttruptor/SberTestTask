package me.podlesnykh.sbertesttask.network

data class CurrencyItem(
    val numCode: Int,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double
)
