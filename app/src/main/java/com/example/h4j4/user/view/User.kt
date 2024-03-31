package com.example.h4j4.user.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.h4j4.ui.theme.H4J4Theme
import com.example.h4j4.user.UserViewModel
import com.example.h4j4.user.viewState.UserViewState

class User : ComponentActivity() {

    val viewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            when (userViewState) {
                UserViewState.Loading -> {
                    Log.d("jade po californie", "loading")
                }

                is UserViewState.LoadedSuccessfully -> {
                    Log.d("jade po californie", "$userViewState")
                }
            }
        }

        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

                content = {}
            )
        }
    }
}