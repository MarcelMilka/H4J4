package com.example.h4j4.user.ui.subsequentialComponents

import android.util.Log
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.UnfocusedTextFieldWhiteElement


@Composable fun MyOutlinedTextield(value: Int, suffix: String, isEnabled: Boolean, isVisible: Boolean, focusRequester: FocusRequester, changedValue:(Int) -> Unit) {

    var valueToDisplay by remember { mutableStateOf(value.toString()) }
    var focusController by remember { mutableStateOf(focusRequester) }
    var suffixColor by remember { mutableStateOf(Color.White) }
    var isError by remember { mutableStateOf(false) }.apply {

        when (valueToDisplay) {

            "0" -> {this.value = true}

            else -> {this.value = false}
        }
    }

    LaunchedEffect(valueToDisplay) {

        when (valueToDisplay) {

            "" -> {
                isError = true
                suffixColor = Color.Red
            }

            "0" -> {
                isError = true
                suffixColor = Color.Red
            }

            else -> {
                isError = false
                suffixColor = Color.White
            }
        }
    }

    if (isVisible) {
        OutlinedTextField(

            value = valueToDisplay,
            onValueChange = {

                Log.d("check what it", it)

                if (it.isEmpty()) {

                    isError = true

                    valueToDisplay = ""
                    changedValue(0)
                }

                else {

                    if (it.count() <= 4) {

                        valueToDisplay = it
                        changedValue(it.toInt())
                    }
                }
            },

            modifier = Modifier
                .width(90.dp)
                .focusRequester(focusController),
            suffix = { Text(text = suffix, color = suffixColor) },

            isError = isError,
            enabled = isEnabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                errorTextColor = Color.Red,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,

                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,

                cursorColor = Color.White,
                errorCursorColor = Color.Red,
            )
        )
    }
}