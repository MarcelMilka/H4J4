package com.example.h4j4.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.ui.theme.CircularProgressIndicatorTrackColor
import com.example.h4j4.ui.theme.ProgressIndicatorTrackColor

@Composable
fun RowScope.OneDayWater(waterDrankThisDay: Int?, waterToDrinkThisDay: Int?) {

    val progressIndicatorTrackColor = if (waterDrankThisDay != null && waterToDrinkThisDay != null) {

        ProgressIndicatorTrackColor
    } else {
        Color.Transparent
    }

    var valueToPass = if (waterDrankThisDay != null && waterToDrinkThisDay != null) {
        (waterDrankThisDay * 160) / waterToDrinkThisDay
    } else {
        0
    }

    if (valueToPass > 160) {
        valueToPass = 160
    }

    Column(

        modifier = Modifier
            .fillMaxHeight()
            .weight(1f, true),

        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,

        content = {

            Column(

                modifier = Modifier
                    .background(color = progressIndicatorTrackColor, shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp))
                    .width(20.dp)
                    .height(160.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                content = {

                    Surface(

                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp))
                            .shadow(elevation = 100.dp, clip = true)
                            .height(maxOf(valueToPass.dp, 0.dp))
                            .width(20.dp),

                        shape = RoundedCornerShape(10.dp),
                        onClick = {},
                        content = {}
                    )
                }
            )

            Spacer(Modifier.height(10.dp))

        }
    )
}