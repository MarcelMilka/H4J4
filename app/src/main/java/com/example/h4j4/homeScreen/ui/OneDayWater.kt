package com.example.h4j4.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.OneDayWater(waterDrankThisDay: Int, waterToDrinkThisDay: Int) {

    var valueToPass = (waterDrankThisDay * 160) / waterToDrinkThisDay

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

//            Divider(Modifier.fillMaxWidth())
            Spacer(Modifier.height(10.dp))
        }
    )
}