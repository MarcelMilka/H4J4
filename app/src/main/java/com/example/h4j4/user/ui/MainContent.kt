package com.example.h4j4.user.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.UnfocusedTextFieldWhiteElement
import com.example.h4j4.user.viewState.UserViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MainContent(uiState: UserViewState) {

//    Water
    var waterIsTracked by remember { mutableStateOf(false) }

    var firstPortionOfWater by remember { mutableStateOf(0) }
    var secondPortionOfWater by remember { mutableStateOf(0) }
    var thirdPortionOfWater by remember { mutableStateOf(0) }

    var heightOfWaterSurface = when (waterIsTracked) {
        true -> {
            220.dp
        }

        false -> {
            50.dp
        }
    }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .weight(21f, true),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,

        content = {

            when (uiState) {

                UserViewState.Loading -> {
                    Text(text = "Water", color = Color.White)
                }

                is UserViewState.LoadedSuccessfully -> {

//                    Filling data
                    firstPortionOfWater = uiState.portionsOfWater.firstPortion
                    secondPortionOfWater = uiState.portionsOfWater.secondPortion
                    thirdPortionOfWater = uiState.portionsOfWater.thirdPortion

//                    Water
                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightOfWaterSurface)
                            .border(
                                width = 4.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(start = 10.dp),

                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,

                        content = {

                            FilterChip(

                                selected = true,
                                onClick = { waterIsTracked = !waterIsTracked },
                                label = { Text(text = "Water", color = Color.White) },
                                colors = FilterChipDefaults.filterChipColors(),
                            )

                            if (waterIsTracked) {

                                Portion(firstPortionOfWater)
                                Portion(secondPortionOfWater)
                                Portion(thirdPortionOfWater)
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun Portion(portionSize: Int) {

//    Overall portion
    var stateOfThePortion = when (portionSize) {
        0 -> {
            StateOfThePortion.ToAdd
        }

        else -> {
            StateOfThePortion.ToEdit
        }
    }

//    OutlinedTextField
    var value by remember { mutableStateOf(portionSize) }
    var enabled by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val keyboardController = LocalSoftwareKeyboardController.current


//    FirstIcon
    var firstIcon: ImageVector
    var firstIconColor: Color

    var secondIcon: ImageVector
    var secondIconColor: Color

    var thirdIcon: ImageVector
    var thirdIconColor: Color

    when (portionSize) {
        0 -> {
            firstIcon = Icons.Rounded.Add
            firstIconColor = Color.Transparent
        }

        else -> {
            firstIcon = Icons.Rounded.Edit
            firstIconColor = Color.White

            secondIcon = Icons.Rounded.Delete
        }
    }

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            IconButton(

                onClick = {
                    focusRequester.requestFocus()
                },
                content = {
                    androidx.compose.material.Icon(
                        imageVector = firstIcon,
                        tint = firstIconColor,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = "$value",
                onValueChange = {

                    when (it.count()) {

                        0 -> {
                            value = 0
                            isError = true
                        }

                        else -> {
                            value = it.toInt()
                            isError = false
                        }
                    }
                },
                suffix = { Text(text = "ml") },
                singleLine = true,
                enabled = enabled,
                colors = OutlinedTextFieldDefaults.colors(

                    focusedTextColor = Color.White,
                    unfocusedTextColor = UnfocusedTextFieldWhiteElement,
                    errorTextColor = Color.Red,

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,

                    cursorColor = Color.White,
                    errorCursorColor = Color.Red,

                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,

                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = UnfocusedTextFieldWhiteElement,
                    errorLabelColor = Color.Red,

                    focusedSuffixColor = Color.White,
                    unfocusedSuffixColor = UnfocusedTextFieldWhiteElement,
                    errorSuffixColor = Color.Red,
                ),

                isError = isError,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                modifier = Modifier
                    .width(90.dp)
                    .focusRequester(focusRequester),

                )


            IconButton(

                onClick = {},
                content = {
                    androidx.compose.material.Icon(
                        imageVector = firstIcon,
                        tint = firstIconColor,
                        contentDescription = null
                    )
                }
            )

            IconButton(

                onClick = {
                    enabled = false
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                    enabled = true
                },
                content = {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Rounded.Done,
                        tint = firstIconColor,
                        contentDescription = null
                    )
                }
            )
        }
    )
}

enum class StateOfThePortion {
    ToAdd,
    ToEdit
}