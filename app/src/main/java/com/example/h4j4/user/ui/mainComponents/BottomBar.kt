package com.example.h4j4.user.ui.mainComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.h4j4.user.dataClasses.UserSettingsChange

@Composable fun ColumnScope.BottomBar(changes:  MutableList<UserSettingsChange>) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .weight(2f, true),

        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            TextButton(

                onClick = {
                    // TODO: function responsible for saving changes
                },
                enabled = true,
                content = { Text(text = "Save changes", color = White) }
            )
        }
    )
}