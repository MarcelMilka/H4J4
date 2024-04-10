package com.example.h4j4.user.ui.mainComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.h4j4.user.viewState.UserViewState

@Composable fun ColumnScope.TopBar(uiState: UserViewState) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .weight(2f, true),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            when(uiState) {
                UserViewState.Loading -> {
                    Text("Loading...", fontSize = 25.sp, color = Color.White)
                }
                is UserViewState.LoadedSuccessfully -> {
                    Text("I want to track...", fontSize = 25.sp, color = Color.White)
                }
            }
        }
    )
}