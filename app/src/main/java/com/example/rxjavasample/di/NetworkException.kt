package com.example.rxjavasample.di

import android.content.Context
import com.example.rxjavasample.ApiConstants
import java.io.IOException

class NetworkException(context: Context) : IOException() {
    override val message: String
        get() = ApiConstants.CHECK_INTERNET_CONNECTION
}