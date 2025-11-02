package com.example.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.main.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint
import example.com.designsystem.theme.WeekTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MainApp() }
    }
}

@Composable
private fun MainApp() {
    WeekTheme {
        val navController = rememberNavController()
        MainNavHost(navController = navController)
    }
}
