package com.example.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsHiveInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithAccessKey = chain.request().url().newBuilder()
            .addQueryParameter(API_KEY_NAME, API_KEY_VALUE)
            .build()


        val requestWithAccessKey = chain.request().newBuilder()
            .url(urlWithAccessKey).build()
        return chain.proceed(requestWithAccessKey)
    }

    companion object {
        const val API_KEY_NAME = "access_key"
        const val API_KEY_VALUE = "cc8734c0dd19da199abaca678ea84b4e"
    }
}