package com.example.h4j4.user.ui.mainComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White

@Composable fun ColumnScope.BottomBar(
    onclickReturnButton: () -> Unit,
    onclickSaveButton: () -> Unit,
    isEnabled: Boolean
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .weight(2f, true),

        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        content = {


            TextButton(

                onClick = { onclickReturnButton() },
                content = { Text(text = "Return to home screen", color = White) }
            )

            TextButton(

                onClick = { onclickSaveButton() },
                enabled = isEnabled,
                content = { Text(text = "Save changes", color = White) }
            )
        }
    )
}