package com.example.h4j4.user.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreen.view.HomeScreen
import com.example.h4j4.user.viewModel.UserViewModel
import com.example.h4j4.user.dataClasses.UserSettingsChange
import com.example.h4j4.user.ui.mainComponents.BottomBar
import com.example.h4j4.user.ui.mainComponents.MainContent
import com.example.h4j4.user.ui.mainComponents.TopBar
import com.example.h4j4.user.ui.subsequentialComponents.MyAlertDialog
import com.example.h4j4.user.viewModel.CommunicatorBetweenLifecycles
import kotlinx.coroutines.flow.asStateFlow

class User : ComponentActivity() {

    val viewModel = UserViewModel()
    private val communicatorBetweenLifecycles = CommunicatorBetweenLifecycles

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            setContent {

                var changes by remember { mutableStateOf(mutableListOf<UserSettingsChange>()) }
                var enableSaving by remember { mutableStateOf(false) }
                var displayDialog by remember { mutableStateOf(false) }

                communicatorBetweenLifecycles.state.observe(this@User) {

                    when (it.displayDialog) {
                        true -> {displayDialog = true}
                        false -> {displayDialog = false}
                    }
                }


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
                            passChanges = {
                                changes.removeAll(changes)
                                changes.addAll(it)

                                when (changes.count()) {

                                    0 -> {
                                        communicatorBetweenLifecycles.disableSaving()
                                        enableSaving = false
                                    }

                                    else -> {
                                        communicatorBetweenLifecycles.enableSaving()
                                        enableSaving = true
                                    }
                                }
                            }
                        )

                        BottomBar(
                            isEnabled = enableSaving,
                            onClick = {
                                viewModel.updateValues(changes)
                                CommunicatorBetweenLifecycles.disableSaving()
                            }
                        )

                        if (displayDialog) {
                            MyAlertDialog(
                                text = "Do you want to leave without saving the changes?",
                                onDismissRequest = {
                                    communicatorBetweenLifecycles.hideDialog()
                                },
                                onClickConfirmButton = {
                                    startActivity(Intent(this@User, HomeScreen::class.java))
                                    communicatorBetweenLifecycles.hideDialog()
                                    communicatorBetweenLifecycles.disableSaving()
                                },
                                onClickDismissButton = {
                                    communicatorBetweenLifecycles.disableSaving()
                                    communicatorBetweenLifecycles.hideDialog()
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Deprecated("Deprecated in Java", ReplaceWith("super.onBackPressed()", "androidx.activity.ComponentActivity"))
    override fun onBackPressed() {

        communicatorBetweenLifecycles.state.observe(this) {

            when (it.savingIsEnabled) {
                true -> { communicatorBetweenLifecycles.displayDialog() }
                false -> { super.onBackPressed() }
            }
        }
    }
}

