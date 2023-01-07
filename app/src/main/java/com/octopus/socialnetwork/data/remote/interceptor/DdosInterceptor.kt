package com.octopus.socialnetwork.data.remote.interceptor

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DdosInterceptor @Inject constructor(): Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        Log.i("DDOS_INTERCEPTOR","request of hashcode ${request.hashCode()} will be added")

        requestsHashCodeBuffer.add(0, request.hashCode())
        while (requestsHashCodeBuffer.last() != request.hashCode()){
            runBlocking {
                delay(DELAY_PERIOD)
            }
        }
        return try {
            chain.proceed(request).also {
                requestsHashCodeBuffer.removeLast()
            }
        } catch (e: Exception) {
            Log.i("DDOS_INTERCEPTOR","catched this exception $e")
            requestsHashCodeBuffer.removeLast()
            chain.proceed(request)
        }
    }

    companion object {
        private const val DELAY_PERIOD = 1500L
        private var requestsHashCodeBuffer = mutableListOf<Int>()
    }
}