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
        Log.i("DDOS_INTERCEPTOR","will add request")
        Log.i("DDOS_INTERCEPTOR","request is ${request.url} with hashcode of ${request.hashCode()}")

        requestsHashCodeBuffer.add(0, request.hashCode())
        Log.i("DDOS_INTERCEPTOR","body :${request.body}")
        Log.i("DDOS_INTERCEPTOR","url :${request.url}")
        Log.i("DDOS_INTERCEPTOR","haderrs :${request.headers}")
        Log.i("DDOS_INTERCEPTOR","methode :${request.method}")

        while (requestsHashCodeBuffer.last() != request.hashCode()){
            Log.i("DDOS_INTERCEPTOR","methode :${requestsHashCodeBuffer.last()}")
            Log.i("DDOS_INTERCEPTOR","body in while :${request.body}")

            runBlocking {
                delay(DELAY_PERIOD)
            }
        }
        return try {
            chain.proceed(request).also {
                requestsHashCodeBuffer.removeLast()
            }
        } catch (e: Exception) {
            requestsHashCodeBuffer.removeLast()
            chain.proceed(request)
        }
    }

    companion object {
        private const val DELAY_PERIOD = 1500L
        private var requestsHashCodeBuffer = mutableListOf<Int>()
    }
}