package com.example.rxjavasample.data

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkAndServerDownCheckerInterceptor(
    private val context: Context,
    private val networkChecker: NetworkChecker,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (networkChecker.isNetworkConnected()) {
                val builder = chain.request().newBuilder()

                val response = chain.proceed(builder.build())

                if (response.code == 500 || response.code == 503) {
                    throw ServerDownException(context)
                }

                return response
            }
        } catch (e: NetworkException) {
            throw NetworkException(context)
        }
        throw IOException("Network connection is not available")
    }
}