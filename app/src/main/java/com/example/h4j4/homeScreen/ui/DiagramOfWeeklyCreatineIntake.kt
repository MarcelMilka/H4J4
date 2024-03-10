package com.example.h4j4.homeScreen.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.ui.theme.CircularProgressIndicatorTrackColor

@Composable
fun DiagramOfWeeklyCreatineIntake(uiState: HomeScreenViewState) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            ),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        content = {

//            Description
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                content = { Text("      Weekly creatine intake", color = Color.White) }
            )

            Divider(modifier = Modifier.fillMaxWidth())

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,

                content = {

                    when (uiState) {
                        HomeScreenViewState.Loading -> {
                            OneDayCreatine(loading = true, null, dayOfWeek = "M")
                            OneDayCreatine(loading = true, null, dayOfWeek = "T")
                            OneDayCreatine(loading = true, null, dayOfWeek = "W")
                            OneDayCreatine(loading = true, null, dayOfWeek = "T")
                            OneDayCreatine(loading = true, null, dayOfWeek = "F")
                            OneDayCreatine(loading = true, null, dayOfWeek = "S")
                            OneDayCreatine(loading = true, null, dayOfWeek = "S")
                        }

                        is HomeScreenViewState.LoadedSuccessfully -> {
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.monday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "M")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.tuesday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "T")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.wednesday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "W")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.thursday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "T")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.friday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "F")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.saturday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "S")
                            OneDayCreatine(loading = false, uiState.weeklyIntakeOfCreatine.sunday / uiState.weeklyIntakeOfWater.dailyGoal, dayOfWeek = "S")
                        }

                        HomeScreenViewState.LoadedUnsuccessfully -> {
                            // TODO:
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun RowScope.OneDayCreatine(loading: Boolean, progress: Int?, dayOfWeek: String) {

    Box(

        modifier = Modifier
            .fillMaxHeight()
            .weight(1f, true),

        contentAlignment = Alignment.Center,

        content = {

            Surface(

                modifier = Modifier
                    .fillMaxSize(),

                color = Color.Transparent,

                onClick = {},
                content = {}
            )

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

                    color = Color.White,
                )
            }

            else if (!loading && progress != null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

                    progress = 0.5f, //progress.toFloat()

                    color = Color.White,
                    trackColor = CircularProgressIndicatorTrackColor
                )
            }

            Text(text = dayOfWeek, color = Color.White)
        }
    )
}