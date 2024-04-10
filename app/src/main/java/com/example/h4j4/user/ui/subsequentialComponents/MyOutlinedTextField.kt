package com.example.h4j4.user.ui.subsequentialComponents

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable fun MyOutlinedTextield(value: Int, suffix: String, readOnly: Boolean, isVisible: Boolean, changedValue:(Int) -> Unit) {

    var value by remember { mutableStateOf(value) }
    var isError by remember { mutableStateOf(false) }
    var isReadOnly by remember { mutableStateOf(readOnly) }

    if (isVisible) {
        OutlinedTextField(

            value = value.toString(),
            onValueChange = {

                if (it.isEmpty()) {

                    isError = true

                    value = 0
                    changedValue(0)
                }

                else {

                    if (it.count() <= 0) {

                        value = it.toInt()
                        changedValue(it.toInt())
                    }
                }
            },

            modifier = Modifier
                .width(90.dp),
            suffix = { Text(suffix) },

            isError = isError,
            readOnly = isReadOnly,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}