package com.example.h4j4.user.ui.subsequentialComponents

import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MyIconButton(icon: ImageVector, tint: Color, isClickable: Boolean, onClick: () -> Unit) {

    IconButton(

        onClick = {onClick()},
        enabled = isClickable,
        content = {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = null
            )
        }
    )
}