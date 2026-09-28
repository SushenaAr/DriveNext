package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.data.AppPreferences
import com.example.myapplication.network.checkInternetConnection
import com.example.myapplication.ui.screens.LoadingScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.NoInternetScreen
import com.example.myapplication.ui.screens.OnboardingScreen

@Composable
fun App() {

    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isOnline by remember {
        mutableStateOf(true)
    }

    var onboardingCompleted by remember {
        mutableStateOf(
            AppPreferences.isOnboardingCompleted(context)
        )
    }

    when {
        isLoading -> {

            LoadingScreen(
                onLoadingFinished = {
                    isOnline = checkInternetConnection(context)
                    isLoading = false
                }
            )
        }

        !isOnline -> {

            NoInternetScreen(
                onRetry = {
                    isOnline = checkInternetConnection(context)
                }
            )
        }

        !onboardingCompleted -> {

            OnboardingScreen(
                onFinished = {
                    AppPreferences.setOnboardingCompleted(context)
                    onboardingCompleted = true
                }
            )
        }

        else -> {

            LoginScreen()
        }
    }
}