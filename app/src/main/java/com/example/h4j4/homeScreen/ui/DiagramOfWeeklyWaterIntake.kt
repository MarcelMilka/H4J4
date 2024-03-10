package com.example.h4j4.homeScreen.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation
import kotlin.time.times

@Composable
fun DiagramOfWeeklyWaterIntake(uiState: HomeScreenViewState) {

    var showLogs: Boolean? by remember { mutableStateOf(false) }

    if (showLogs != null) {


    }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            ),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        content = {

//            Description:
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                content = { Text("      Weekly water intake", color = Color.White) }
            )

            Divider(modifier = Modifier.fillMaxWidth())

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                content = {

//                    Scale
                    Column(

                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp),

                        horizontalAlignment = Alignment.CenterHorizontally,

                        content = {

                            when (uiState) {

                                HomeScreenViewState.Loading -> {

                                    Spacer(Modifier.height(10.dp))

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(30.dp),

                                        strokeCap = StrokeCap.Round,
                                        color = Color.White
                                    )

                                    Spacer(Modifier.height(35.dp))

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(30.dp),

                                        strokeCap = StrokeCap.Round,
                                        color = Color.White
                                    )

                                    Spacer(Modifier.height(35.dp))

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(30.dp),

                                        strokeCap = StrokeCap.Round,
                                        color = Color.White
                                    )

                                    Spacer(Modifier.height(10.dp))
                                }

                                is HomeScreenViewState.LoadedSuccessfully -> {

                                    Spacer(Modifier.height(10.dp))

                                    Text("${(uiState.weeklyIntakeOfWater.dailyGoal.toDouble()) / 1000} L", color = Color.White)

                                    Spacer(Modifier.height(60.dp))

                                    Text("${((uiState.weeklyIntakeOfWater.dailyGoal.toDouble()) / 2) / 1000} L", color = Color.White)

                                    Spacer(Modifier.height(55.dp))

                                    Text("0 L", color = Color.White)

                                    Spacer(Modifier.height(10.dp))
                                }

                                HomeScreenViewState.LoadedUnsuccessfully -> {}
                            }
                        }
                    )

                    Row(

                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .border(
                                width = 4.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            ),

                        content = {

                            when (uiState) {
                                HomeScreenViewState.Loading -> {}

                                is HomeScreenViewState.LoadedSuccessfully -> {

                                    if (uiState.whatIsTracked.water) {

                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.monday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.tuesday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.wednesday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.thursday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.friday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.saturday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                        OneDayWater(waterDrankThisDay = uiState.weeklyIntakeOfWater.sunday, waterToDrinkThisDay = uiState.weeklyIntakeOfWater.dailyGoal)
                                    }

                                    else {
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                        OneDayWater(waterDrankThisDay = null, waterToDrinkThisDay = null)
                                    }
                                }

                                HomeScreenViewState.LoadedUnsuccessfully -> {
                                    Text("Failed to load data")
                                }
                            }

                        }
                    )
                }
            )

            Divider(modifier = Modifier.fillMaxWidth())

//            Days of week
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                content = {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp),

                        content = {}
                    )

                    Row (

                        modifier = Modifier
                            .fillMaxSize(),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        content = {

                            OneDayAbbreviation("M")
                            OneDayAbbreviation("T")
                            OneDayAbbreviation("W")
                            OneDayAbbreviation("T")
                            OneDayAbbreviation("F")
                            OneDayAbbreviation("S")
                            OneDayAbbreviation("S")
                        }
                    )
                }
            )
        }
    )
}