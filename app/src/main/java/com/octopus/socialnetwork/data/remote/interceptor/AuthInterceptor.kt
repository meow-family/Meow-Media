package com.octopus.socialnetwork.data.remote.interceptor

import com.octopus.socialnetwork.BuildConfig.API_KEY
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
        return chain.proceed((chain.request().newBuilder().url(original).build()))
    }

    companion object {
        const val API_KEY_QUERY = "api_key_token"
    }
}