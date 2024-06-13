package com.example.rxjavasample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.layoutDirection
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rxjavasample.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        window.decorView.layoutDirection = Locale.getDefault().layoutDirection
    }
}