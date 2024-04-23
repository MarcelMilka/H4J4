package com.example.h4j4.user.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.UserViewModel
import com.example.h4j4.user.dataClasses.UserSettingsChange
import com.example.h4j4.user.ui.mainComponents.BottomBar
import com.example.h4j4.user.ui.mainComponents.MainContent
import com.example.h4j4.user.ui.mainComponents.TopBar

class User : ComponentActivity() {

    val viewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            setContent {

                var listOfChanges by remember { mutableStateOf(mutableListOf<UserSettingsChange>()) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,

                    content = {

                        TopBar(userViewState)

                        MainContent(
                            uiState = userViewState,
                            listOfChanges = listOfChanges,
                            passChanges = {

//                                listOfChanges.removeAll(listOfChanges)
                                if (listOfChanges.isEmpty()) {
                                    listOfChanges.addAll(it)
                                }

                                else {
                                    listOfChanges.clear()
                                    listOfChanges.addAll(it)

                                }

                                Log.d("hey there delilah", "User.kt, MainContent(passChanges) $it")

                            }
                        )

                        Button(
                            onClick = { Log.d("hey there delilah", "User.kt, Button $listOfChanges") },
                            content = {}
                        )

                        BottomBar(
                            onClick = {
                                Log.d("hey there delilah", "User.kt, BottomBar $listOfChanges")
                                viewModel.updateValues(listOfChanges)
                                listOfChanges.clear()
                            }
                        )
                    }
                )
            }
        }
    }
}