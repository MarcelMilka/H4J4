package com.example.h4j4.user.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.H4J4Theme
import com.example.h4j4.user.UserViewModel
import com.example.h4j4.user.ui.BottomBar
import com.example.h4j4.user.ui.MainContent
import com.example.h4j4.user.ui.TopBar
import com.example.h4j4.user.viewState.UserViewState

class User : ComponentActivity() {

    val viewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            setContent {

//                Water

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,

                    content = {

                        TopBar(userViewState)

                        Divider()

                        MainContent(userViewState)

                        Divider()

                        BottomBar()
                    }
                )
            }
        }
    }
}