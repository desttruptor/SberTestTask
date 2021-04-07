package me.podlesnykh.sbertesttask.network

sealed class Result {
    data class Success<out T>(val data: T)
    data class Error(val exception: String)
}
