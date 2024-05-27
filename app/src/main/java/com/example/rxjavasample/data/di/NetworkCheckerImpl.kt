package com.example.rxjavasample.data.di

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkCheckerImpl(private val context: Context) : NetworkChecker {
    override fun isNetworkConnected(): Boolean {
        if (!NetworkUtils.isNetworkConnected(context)) {
            throw NetworkException(context)
        }
        return true
    }

    override fun checkNetworkConnectionOrThrow(): Flow<Unit> {
        return flow {
            if (!NetworkUtils.isNetworkConnected(context)) {
                throw NetworkException(context)
            }
            emit(Unit)
        }
    }
}
