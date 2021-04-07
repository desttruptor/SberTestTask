package me.podlesnykh.sbertesttask.network

import java.io.IOException

sealed class Result {
    data class Success<out T>(val data: T)
    data class Error(val message: String)
}
