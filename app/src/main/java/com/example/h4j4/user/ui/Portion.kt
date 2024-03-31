package com.example.h4j4.user.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.ui.theme.TextFieldFocused
import com.example.h4j4.ui.theme.TextFieldUnfocused
import com.example.h4j4.user.viewState.UserViewState

@Composable
fun theRightPortion(portion: Int) {
    var value by remember { mutableStateOf(portion) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(100.dp),
            value = if (value != 0) value.toString() else "",
            onValueChange = {
                value = if (it.isEmpty()) {
                    0
                } else {
                    it.toIntOrNull() ?: value
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = TextFieldUnfocused,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Amount") },
            placeholder = { Text(text = "Enter amount") },
            singleLine = true,
            trailingIcon = { Text(text = "ml") },
            isError = value <= 0,
            visualTransformation = VisualTransformation.None
        )
    }
}


@Composable fun Portion(portion: Int, waterOrCreatine: WaterOrCreatine) {

    val suffix = when (waterOrCreatine) {
        WaterOrCreatine.WATER -> {"ml"}
        WaterOrCreatine.CREATINE -> {"g"}
    }

//    Text field
    var activateTextField by remember { mutableStateOf(FocusRequester()) }
    var textFieldIsActive by remember { mutableStateOf(false) }
    var textFieldAmount by remember { mutableStateOf(portion) }

//  Portion
    var portionIsSet by remember { mutableStateOf(false) }

    if (portion == 0) {
        portionIsSet = false
    }
    else {
        portionIsSet = true
    }


    var textColor =
        if (portion == 0) { Color.Transparent }
        else { TextFieldUnfocused }

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            IconButton(

                onClick = {
                    activateTextField.requestFocus()
                    textFieldIsActive = true
                },
                content = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.White) }
            )

            OutlinedTextField(

                modifier = Modifier
                    .focusRequester(activateTextField)
                    .width(100.dp),

                value = textFieldAmount.toString(),

                onValueChange = {
                                textFieldAmount = it.toInt()
                },

                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = TextFieldUnfocused,

                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,

                    unfocusedTextColor = textColor,
                    focusedTextColor = textColor,

                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,

                    unfocusedSuffixColor = Color.Transparent,
                    focusedSuffixColor = Color.Transparent,

                    disabledTrailingIconColor = Color.Transparent,
                    focusedTrailingIconColor = Color.Transparent,

                    disabledSuffixColor = TextFieldFocused,
                    errorSuffixColor = Color.Red,

                ),


                supportingText = {
                    if (textFieldAmount == 0 || textFieldAmount == null) {
                        Text("Too small portion.")
                    }
                },


                label = { Text(text = "Daily amount of water to drink") },

                suffix = { Text(text = "ml") },

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )

            if (textFieldIsActive) {

//                Save
                IconButton(

                    enabled = true,
                    onClick = {activateTextField.requestFocus()},
                    content = { Icon(imageVector = Icons.Rounded.Check, contentDescription = null, tint = Color.White) }
                )

//                Cancel/delete
                IconButton(

                    enabled = true,
                    onClick = {activateTextField.requestFocus()},
                    content = { Icon(imageVector = Icons.Rounded.Delete, contentDescription = null, tint = Color.White) }
                )
            }
        }
    )
}