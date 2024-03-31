package com.example.h4j4.user.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.ui.theme.TextFieldFocused
import com.example.h4j4.ui.theme.TextFieldUnfocused
import com.example.h4j4.user.ui.Portion
import com.example.h4j4.user.ui.theRightPortion
import com.example.h4j4.user.viewModel.UserViewModel
import com.example.h4j4.user.viewState.UserViewState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class User : ComponentActivity() {

//    In this activity the data gets fetched only once, there's not any data fetched by snapshotListener etc.
//    Data is stored as variables.

//    *User enters the activity, data gets fetched and stored in variables.
//    *User changes value of the first portion of water - data gets updated automatically if it

    var viewModel = UserViewModel() // earlier repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.observe(this@User) {userViewState ->

            setContent {

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {

                        DescriptionOfTheActivity(userViewState)
                        Water(userViewState)
//                    Creatine

                    }
                )
            }
        }
    }
}

@Composable fun DescriptionOfTheActivity(uiState: UserViewState) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,

        content = {

            when (uiState) {
                UserViewState.Loading -> {
                    Text(text = "Loading...", fontSize = 25.sp, color = Color.White)
                }
                is UserViewState.LoadedSuccessfully -> {
                    Text(text = "I want to track...", fontSize = 25.sp, color = Color.White)
                }
                UserViewState.FailedToLoadData -> {
                    Text(text = "Failed to load data.", fontSize = 25.sp, color = Color.White)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun Water(uiState: UserViewState) {

    Column(

        modifier = Modifier
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,

        content = {

            when (uiState) {

                UserViewState.Loading -> {}

                is UserViewState.LoadedSuccessfully -> {

                    var waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }

                    FilterChip(

                        selected = waterIsTracked,

                        onClick = {waterIsTracked = !waterIsTracked},

                        label = { Text("Water") },

                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color.White,
                            labelColor = Color.White,
                            selectedLabelColor = Color.Black
                        ),

                        leadingIcon = if (waterIsTracked) {
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Done,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        }
                        else {
                            null
                        },
                    )

                    if (waterIsTracked) {

                        theRightPortion(uiState.portionsOfWater.firstPortion)
                    }
                }

                UserViewState.FailedToLoadData -> {}
            }
        }
    )
}