package com.example.rxjavasample.data

import kotlinx.coroutines.flow.Flow

interface NetworkChecker {
    fun isNetworkConnected(): Boolean

    fun checkNetworkConnectionOrThrow(): Flow<Unit>
}