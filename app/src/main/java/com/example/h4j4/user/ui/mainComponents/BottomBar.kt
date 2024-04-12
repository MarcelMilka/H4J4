package com.example.h4j4.user.ui.mainComponents

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.h4j4.homeScreen.view.HomeScreen
import com.example.h4j4.user.view.User

@Composable fun ColumnScope.BottomBar() {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .weight(2f, true),

        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            TextButton(

                onClick = {},
                content = { Text(text = "Return to home screen", color = White) }
            )

            TextButton(

                onClick = {},
                enabled = false,
                content = { Text(text = "Save changes", color = White) }
            )
        }
    )
}