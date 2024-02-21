package com.example.h4j4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.h4j4.ui.theme.Sixty

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        a reference to the splash screen
        installSplashScreen().apply {}

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
