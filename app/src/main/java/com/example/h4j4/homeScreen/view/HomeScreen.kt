package com.example.h4j4.homeScreen.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.h4j4.homeScreen.viewModel.HomeScreenViewModel
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.ui.theme.Sixty

class HomeScreen : ComponentActivity() {

    val viewModel = HomeScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        a reference to the splash screen
        installSplashScreen().apply {}

        viewModel.publicCurrentState.observe(this@HomeScreen) {

            when (it) {
                HomeScreenViewState.Loading -> {
                    Log.d("test of vm", "loading")
                }

                is HomeScreenViewState.LoadedSuccessfully -> {
                    Log.d("test of vm", "loaded successfully, ${it.x}")
                }

                HomeScreenViewState.LoadedUnsuccessfully -> {
                    Log.d("test of vm", "loaded unsuccessfully")
                }
            }
        }

        setContent {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Sixty),

                content = { Text("hey there!") }
            )
        }
    }
}
