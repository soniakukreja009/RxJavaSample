package com.example.rxjavasample.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Check whether the network is available or not.
 * permission added to manifest file.
 */
object NetworkUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val hasConnectedToMobileData =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR,
                ) ?: false

        val hasConnectedToWifi =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI,
                ) ?: false

        return hasConnectedToMobileData || hasConnectedToWifi
    }
}
