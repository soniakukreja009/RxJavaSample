package com.example.rxjavasample.data

import android.content.Context
import java.io.IOException

class NetworkException(context: Context) : IOException() {
    override val message: String
        get() = "check internet connection"
}