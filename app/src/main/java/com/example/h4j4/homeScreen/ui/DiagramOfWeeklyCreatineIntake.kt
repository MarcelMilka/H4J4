package com.example.h4j4.homeScreen.ui

import android.util.Log
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
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "M")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "T")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "W")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "T")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "F")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "S")
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "S")
                        }

                        is HomeScreenViewState.LoadedSuccessfully -> {

                            if (uiState.whatIsTracked.creatine) {

                                val dailyGoal = uiState.weeklyIntakeOfCreatine.dailyGoal

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.monday, dailyGoal = dailyGoal,dayOfWeek = "M")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.tuesday, dailyGoal = dailyGoal,dayOfWeek = "T")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.wednesday, dailyGoal = dailyGoal,dayOfWeek = "W")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.thursday, dailyGoal = dailyGoal,dayOfWeek = "T")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.friday, dailyGoal = dailyGoal,dayOfWeek = "F")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.saturday, dailyGoal = dailyGoal,dayOfWeek = "S")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.sunday, dailyGoal = dailyGoal,dayOfWeek = "S")
                            }

                            else {

                                Log.d("DiagramOfWeeklyCreatineIntake", "no i git")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "M")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "T")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "W")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "T")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "F")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "S")
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, dailyGoal = null, dayOfWeek = "S")
                            }
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
fun RowScope.OneDayCreatine(loading: Boolean, ingestedAmountOfCreatine: Int?, dailyGoal: Int?, dayOfWeek: String) {

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

            else if (!loading && ingestedAmountOfCreatine != null && dailyGoal != null) {

                val progress = (ingestedAmountOfCreatine.toFloat() * 1f) / dailyGoal.toFloat()

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

//                    1f - daily amount of creatine
//                    xf - ingested amount of creatine

                    progress = progress,

                    color = Color.White,
                    trackColor = CircularProgressIndicatorTrackColor
                )
            }

            else if (!loading && ingestedAmountOfCreatine == null && dailyGoal == null) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

                    progress = 0f,

                    color = Color.White,
                    trackColor = CircularProgressIndicatorTrackColor
                )
            }

            Text(text = dayOfWeek, color = Color.White)

        }
    )
}