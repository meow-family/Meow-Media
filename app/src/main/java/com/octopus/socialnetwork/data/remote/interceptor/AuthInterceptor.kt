package com.octopus.socialnetwork.data.remote.interceptor

import com.octopus.socialnetwork.BuildConfig.API_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_KEY)
            .build()

        requestsHashCodeBuffer.add(0, original.hashCode())
        while (requestsHashCodeBuffer.last() != original.hashCode()) {
            runBlocking {
                delay(DELAY_PERIOD)
            }
        }

        return chain.proceed(
            chain.request().newBuilder().url(original).build()
        ).also {
            requestsHashCodeBuffer.removeLast()
        }

    }

    companion object {
        private const val DELAY_PERIOD = 1500L
        private var requestsHashCodeBuffer = mutableListOf<Int>()
        const val API_KEY_QUERY = "api_key_token"
    }
}