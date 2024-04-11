package com.example.h4j4.user.ui.subsequentialComponents

import android.util.Log
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.UnfocusedTextFieldWhiteElement


@Composable fun MyOutlinedTextield(value: Int, suffix: String, readOnly: Boolean, isVisible: Boolean, changedValue:(Int) -> Unit) {

    var value by remember { mutableStateOf(value) }
    var isError by remember {

        mutableStateOf(

            when(value) {

                0 -> {true}

                else -> {false}
            }
        )
    }
    var isReadOnly by remember { mutableStateOf(readOnly) }

    if (isVisible) {
        OutlinedTextField(

            value = value.toString(),
            onValueChange = {

                Log.d("check if isError", "isError = $isError")

                if (it.isEmpty()) {

                    isError = true

                    value = 0
                    changedValue(0)
                }

                else {

                    if (it.count() <= 4) {

                        value = it.toInt()
                        changedValue(it.toInt())
                    }
                }
            },

            modifier = Modifier
                .width(90.dp),
            suffix = { Text(suffix) },

            isError = isError,
            readOnly = false,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = Color.White,
                unfocusedTextColor = UnfocusedTextFieldWhiteElement,
                disabledTextColor = UnfocusedTextFieldWhiteElement,
                errorTextColor = Color.Red,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,

                cursorColor = Color.White,
                errorCursorColor = Color.Red,

                focusedSuffixColor = Color.White,
                unfocusedSuffixColor = UnfocusedTextFieldWhiteElement,
                errorSuffixColor = Color.Red,
            )
        )
    }
}