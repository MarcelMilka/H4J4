package com.example.h4j4.user.ui.subsequentialComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.Sixty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog(

    text: String,
    onDismissRequest: () -> Unit,

    onClickConfirmButton: () -> Unit,
    onClickDismissButton: () -> Unit
    ) {

    AlertDialog(

        containerColor = Sixty,

        text = {Text(text = text, color = Color.White)},

        onDismissRequest = {onDismissRequest()},

        confirmButton = {

            TextButton(
                onClick = {onClickConfirmButton()},
                content = { Text(text = "Confirm", color = Color.White) }
            )
        },

        dismissButton = {
            TextButton(
                onClick = {onClickDismissButton()},
                content = { Text("Dismiss", color = Color.White) }
            )
        }
    )
}
