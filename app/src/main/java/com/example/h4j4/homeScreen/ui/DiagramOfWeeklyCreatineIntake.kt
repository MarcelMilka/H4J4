package com.example.h4j4.homeScreen.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.h4j4.BottomSheetLauncher
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.ui.theme.CircularProgressIndicatorTrackColor
import java.time.DayOfWeek

@Composable
fun DiagramOfWeeklyCreatineIntake(
    uiState: HomeScreenViewState,
    bottomSheetLauncher: (BottomSheetLauncher) -> Unit
) {

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

//          Description
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
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.MONDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.TUESDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.WEDNESDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.THURSDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.FRIDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.SATURDAY, bottomSheetLauncher = {})
                            OneDayCreatine(loading = true, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.SUNDAY, bottomSheetLauncher = {})
                        }

                        is HomeScreenViewState.LoadedSuccessfully -> {

                            if (uiState.whatIsTracked.creatine) {

                                val dailyGoal = uiState.weeklyIntakeOfCreatine.dailyGoal

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.monday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.MONDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.tuesday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.TUESDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.wednesday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.WEDNESDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.thursday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.THURSDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.friday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.FRIDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.saturday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.SATURDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })

                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = uiState.weeklyIntakeOfCreatine.sunday, creatineToIngestThisDay = dailyGoal,representedDayOfWeek = DayOfWeek.SUNDAY, bottomSheetLauncher = {
                                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = it.dayOfWeek, launch = it.launch, waterOrCreatine =  WaterOrCreatine.CREATINE))
                                })
                            }

                            else {
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.MONDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.TUESDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.WEDNESDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.THURSDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.FRIDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.SATURDAY, bottomSheetLauncher = {})
                                OneDayCreatine(loading = false, ingestedAmountOfCreatine = null, creatineToIngestThisDay = null, representedDayOfWeek = DayOfWeek.SUNDAY, bottomSheetLauncher = {})
                            }
                        }

                        HomeScreenViewState.LoadedUnsuccessfully -> {}
                    }
                }
            )
        }
    )
}

@Composable
fun RowScope.OneDayCreatine(
    ingestedAmountOfCreatine: Int?,
    creatineToIngestThisDay: Int?,
    representedDayOfWeek: DayOfWeek,
    bottomSheetLauncher: (BottomSheetLauncher) -> Unit,
    loading: Boolean
) {

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

                onClick = {
                    bottomSheetLauncher(BottomSheetLauncher(dayOfWeek = representedDayOfWeek, launch = true, waterOrCreatine = WaterOrCreatine.CREATINE))
                },
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

            else if (!loading && ingestedAmountOfCreatine != null && creatineToIngestThisDay != null) {

                val progress = (ingestedAmountOfCreatine.toFloat() * 1f) / creatineToIngestThisDay.toFloat()

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

                    progress = progress,

                    color = Color.White,
                    trackColor = CircularProgressIndicatorTrackColor
                )
            }

            else if (!loading && ingestedAmountOfCreatine == null && creatineToIngestThisDay == null) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),

                    strokeCap = StrokeCap.Round,

                    progress = 0f,

                    color = Color.White,
                    trackColor = CircularProgressIndicatorTrackColor
                )
            }

            Text(text = "${representedDayOfWeek.toString()[0]}", color = Color.White)

        }
    )
}