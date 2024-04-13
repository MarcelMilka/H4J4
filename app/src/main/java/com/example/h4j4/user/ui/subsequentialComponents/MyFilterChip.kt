package com.example.h4j4.user.ui.subsequentialComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun MyFilterChip(isTracked: Boolean, text: String, changeTracking: (Boolean) -> Unit) {

    FilterChip(

        selected = true,
        onClick = { changeTracking(!isTracked) },
        label = { Text(text = text, color = Color.White) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color.Transparent,

            ),
        trailingIcon = {
            if (isTracked) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Rounded.Check,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}
