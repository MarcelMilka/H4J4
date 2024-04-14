package com.example.h4j4.user.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreen.view.HomeScreen
import com.example.h4j4.user.UserViewModel
import com.example.h4j4.user.ui.mainComponents.BottomBar
import com.example.h4j4.user.ui.mainComponents.MainContent
import com.example.h4j4.user.ui.mainComponents.TopBar

class User : ComponentActivity() {

    val viewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            setContent {

                var buttonToSaveChangesIsEnabled by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,

                    content = {

                        TopBar(userViewState)

                        MainContent(userViewState)

                        BottomBar(
                            onclickReturnButton = {startActivity(Intent(this@User, HomeScreen::class.java))},
                            onclickSaveButton = {},
                            isEnabled = buttonToSaveChangesIsEnabled
                        )
                    }
                )
            }
        }
    }
}

//buttons in the BottomBar.kt work properly, fixed the bug which did not launch keyboard after clicking button responsible for editing value.