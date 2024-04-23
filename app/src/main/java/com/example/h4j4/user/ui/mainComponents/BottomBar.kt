package com.example.h4j4.user.ui.mainComponents

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.dataClasses.UserSettingsChange

@Composable fun ColumnScope.BottomBar(
    onClick: () -> Unit) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp)
            .weight(2f, true),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            TextButton(

                onClick = { onClick() },
                enabled = true,
                content = { Text(text = "Save changes", color = White) }
            )
        }
    )
}