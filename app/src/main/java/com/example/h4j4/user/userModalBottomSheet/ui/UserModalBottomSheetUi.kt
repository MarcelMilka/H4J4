package com.example.h4j4.user.userModalBottomSheet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.ui.theme.Sixty

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun UserModalBottomSheet (
    displayModalBottomSheet: Boolean,
    modalBottomSheetRepresents: WaterOrCreatine?,
    currentDailyGoal: Int,
    sheetState: SheetState,

    onDismissRequest: (Int) -> Unit
) {

    if (displayModalBottomSheet && modalBottomSheetRepresents != null) {

//        Text
        var suffix by remember {
            mutableStateOf(

                if (modalBottomSheetRepresents != null) {

                    when (modalBottomSheetRepresents) {
                        WaterOrCreatine.WATER -> "ml"
                        WaterOrCreatine.CREATINE -> "g"
                    }
                } else {
                    ""
                }

            )
        }

//        Slider
        val valueRange = when (modalBottomSheetRepresents) {
            WaterOrCreatine.WATER -> {
                1000f..4500f
            }

            WaterOrCreatine.CREATINE -> {
                1f..25f
            }
        }

//        Daily goal
        var dailyGoalBeforeChange by remember { mutableStateOf(currentDailyGoal.toFloat()) }
        var dailyGoalAfterChange by remember { mutableStateOf(dailyGoalBeforeChange) }

        ModalBottomSheet(

            onDismissRequest = {
                onDismissRequest(dailyGoalAfterChange.toInt()) },
            modifier = Modifier.fillMaxHeight(0.25f),
            sheetState = sheetState,
            containerColor = Sixty,
            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    content = {

                        Text(text = "Daily, I want to ingest ${dailyGoalAfterChange.toInt()} $suffix", color = Color.White)

                        Slider(

                            modifier = Modifier
                                .fillMaxWidth(0.75f),

                            value = dailyGoalAfterChange,

                            onValueChange = {dailyGoalAfterChange = it},

                            onValueChangeFinished = {

                                if (dailyGoalAfterChange == 0f) {
                                    dailyGoalAfterChange = dailyGoalBeforeChange
                                }
                            },

                            valueRange = valueRange, // TODO: the value has to change by every 50

                            colors = SliderDefaults.colors() // TODO: change colors
                        )
                    }
                )
            }
        )
    }
}