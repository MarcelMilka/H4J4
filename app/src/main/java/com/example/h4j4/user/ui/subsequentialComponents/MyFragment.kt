package com.example.h4j4.user.ui.subsequentialComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable fun MyFragment(
    heightOfSurface: Int,
    isTracked: Boolean,
    subsequentSubstanceIsTracked: Boolean,
    dailyGoal: Int,
    suffix: String,
    waterOrCreatine: String,
    firstPortion: Int,
    secondPortion: Int,
    thirdPortion: Int,
    displayModalBottomSheet: () -> Unit,
    onIsTrackedChanged: (Boolean) -> Unit,
    onFirstPortionChanged: (Int) -> Unit,
    onSecondPortionChanged: (Int) -> Unit,
    onThirdPortionChanged: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightOfSurface.dp)
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        content = {
            MyFilterChip(isTracked = isTracked, subsequentSubstanceIsTracked = subsequentSubstanceIsTracked ,text = waterOrCreatine) {
                onIsTrackedChanged(it)
            }
            if (isTracked) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Text(
                            text = "Daily amount of ${waterOrCreatine.lowercase()} to ingest:",
                            color = Color.White
                        )
                        TextButton(
                            onClick = {displayModalBottomSheet()},
                            content = {
                                Text(
                                    text = "$dailyGoal $suffix",
                                    color = Color.White
                                )
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.Edit,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        )
                    }
                )
                Portion(firstPortion, suffix) { newValueOfPortion ->
                    onFirstPortionChanged(newValueOfPortion)
                }
                if (firstPortion != 0) {
                    Portion(secondPortion, suffix) { newValueOfPortion ->
                        onSecondPortionChanged(newValueOfPortion)
                    }
                }
                if (secondPortion != 0) {
                    Portion(thirdPortion, suffix) { newValueOfPortion ->
                        onThirdPortionChanged(newValueOfPortion)
                    }
                }
            }
        }
    )
}
