package com.example.h4j4.homeScreen.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LabelImportant
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.ui.theme.*
import java.time.LocalDate

@Composable
fun ControllPanel(uiState: HomeScreenViewState) {

    val currentDayOfWeek= LocalDate.now().dayOfWeek.toString().lowercase()

    Column (

        modifier = Modifier
            .width(220.dp)
            .height(340.dp)
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            ),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,

        content = {

//            Creatine and water circular progress indicators
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp),

                contentAlignment = Alignment.Center,

                content = {

                    when (uiState) {
                        HomeScreenViewState.Loading -> {

//                            Water
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(140.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 8.dp,

                                color = WaterToDrink
                            )

//                            Creatine
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(110.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 6.dp,

                                color = CreatineToIngest
                            )
                        }

                        is HomeScreenViewState.LoadedSuccessfully -> {

                            val waterIngestedToday = if (uiState.whatIsTracked.water) {
                                when (currentDayOfWeek) {
                                    "monday" -> uiState.weeklyIntakeOfWater.monday
                                    "tuesday" -> uiState.weeklyIntakeOfWater.tuesday
                                    "wednesday" -> uiState.weeklyIntakeOfWater.wednesday
                                    "thursday" -> uiState.weeklyIntakeOfWater.thursday
                                    "friday" -> uiState.weeklyIntakeOfWater.friday
                                    "saturday" -> uiState.weeklyIntakeOfWater.saturday
                                    "sunday" -> uiState.weeklyIntakeOfWater.sunday

                                    else -> {0f}
                                }.toFloat()
                            }
                            else {
                                0f
                            }

                            val waterToIngestToday = uiState.weeklyIntakeOfWater.dailyGoal.toFloat()
                            val creatineIngestedToday = if (uiState.whatIsTracked.creatine) {
                                when (currentDayOfWeek) {
                                    "monday" -> uiState.weeklyIntakeOfCreatine.monday
                                    "tuesday" -> uiState.weeklyIntakeOfCreatine.tuesday
                                    "wednesday" -> uiState.weeklyIntakeOfCreatine.wednesday
                                    "thursday" -> uiState.weeklyIntakeOfCreatine.thursday
                                    "friday" -> uiState.weeklyIntakeOfCreatine.friday
                                    "saturday" -> uiState.weeklyIntakeOfCreatine.saturday
                                    "sunday" -> uiState.weeklyIntakeOfCreatine.sunday

                                    else -> {0f}
                                }.toFloat()
                            }
                            else {
                                0f
                            }

                            val creatineToIngestToday = uiState.weeklyIntakeOfCreatine.dailyGoal.toFloat()

//                            Water
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(140.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 8.dp,

                                progress = if (uiState.whatIsTracked.water) {
                                    waterIngestedToday/waterToIngestToday
                                }
                                else {0f},

                                color = WaterDrank,
                                trackColor = WaterToDrink
                            )

//                            Creatine
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(110.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 6.dp,

                                progress = if (uiState.whatIsTracked.creatine) {
                                    creatineIngestedToday/creatineToIngestToday
                                }
                                else {0f},

                                color = CreatineIngested,
                                trackColor = CreatineToIngest
                            )
                        }

                        HomeScreenViewState.LoadedUnsuccessfully -> {

//                            Water
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(140.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 8.dp,

                                progress = 0f,

                                color = WaterDrank,
                                trackColor = WaterToDrink
                            )

//                            Creatine
                            CircularProgressIndicator(

                                modifier = Modifier
                                    .size(110.dp),

                                strokeCap = StrokeCap.Round,
                                strokeWidth = 6.dp,

                                progress = 0f,

                                color = CreatineIngested,
                                trackColor = CreatineToIngest
                            )
                        }
                    }
                }
            )

            Text("   Add water", color = Color.White)

            Spacer(modifier = Modifier.height(5.dp))

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),

                horizontalArrangement = Arrangement.SpaceEvenly,

                content = {

                    when (uiState) {
                        HomeScreenViewState.Loading -> {
                            AssistChipLoading()
                            AssistChipLoading()
                            AssistChipLoading()
                        }
                        is HomeScreenViewState.LoadedSuccessfully -> {
                            if (uiState.whatIsTracked.water) {
                                AssistChipLoaded(uiState.portionsOfWater.firstPortion)
                                AssistChipLoaded(uiState.portionsOfWater.secondPortion)
                                AssistChipLoaded(uiState.portionsOfWater.thirdPortion)
                            }

                            else {
                                AssistChipLoaded(0)
                                AssistChipLoaded(0)
                                AssistChipLoaded(0)
                            }
                        }

                        HomeScreenViewState.LoadedUnsuccessfully -> TODO()
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))


            Text("   Add creatine", color = Color.White)

            Spacer(modifier = Modifier.height(5.dp))

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),

                horizontalArrangement = Arrangement.SpaceEvenly,

                content = {

                    when (uiState) {
                        HomeScreenViewState.Loading -> {
                            AssistChipLoading()
                            AssistChipLoading()
                            AssistChipLoading()
                        }
                        is HomeScreenViewState.LoadedSuccessfully -> {
                            if (uiState.whatIsTracked.creatine) {
                                AssistChipLoaded(uiState.portionsOfCreatine.firstPortion)
                                AssistChipLoaded(uiState.portionsOfCreatine.secondPortion)
                                AssistChipLoaded(uiState.portionsOfCreatine.thirdPortion)
                            }

                            else {
                                AssistChipLoaded(0)
                                AssistChipLoaded(0)
                                AssistChipLoaded(0)
                            }
                        }
                        HomeScreenViewState.LoadedUnsuccessfully -> TODO()
                    }
                }
            )


            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                content = {

                    Text("     Drank water:", color = Color.White, fontSize = 8.sp)
                    Icon(imageVector = Icons.AutoMirrored.Filled.LabelImportant, contentDescription = null, tint = WaterDrank, modifier = Modifier.size(12.dp).padding(top = 2.dp))

                    Text("  Ingested creatine:", color = Color.White, fontSize = 8.sp)
                    Icon(imageVector = Icons.AutoMirrored.Filled.LabelImportant, contentDescription = null, tint = CreatineIngested, modifier = Modifier.size(12.dp).padding(top = 2.dp))
                }
            )
        }
    )
}


@Preview
@Composable
fun AssistChipLoading() {
    AssistChip(
        modifier = Modifier
            .width(65.dp),
        onClick = {},
        label = {

            Box(

                modifier = Modifier
                    .fillMaxSize(),

                contentAlignment = Alignment.Center,

                content = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(30.dp),

                        strokeCap = StrokeCap.Round,

                        color = Color.White,
                    )
                }
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = Color.White,
            )
        },
    )
}

@Composable
fun AssistChipLoaded(valueOfPortion: Int) {

    val textToDisplay = if (valueOfPortion != 0) {
        valueOfPortion.toString()
    }
    else {
        "portion"
    }

    AssistChip(

        modifier = Modifier
            .width(65.dp),


        onClick = {

            if (valueOfPortion != 0) {
                // TODO: function which adds another portion
            }
            else {
                // TODO: function which directs to settings
            }
        },

        label = {

            Box(

                modifier = Modifier
                    .fillMaxSize(),

                contentAlignment = Alignment.Center,

                content = {Text(textToDisplay, color = Color.White, fontSize = 8.sp) }
            )
        },

        leadingIcon = {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = Color.White,
            )
        },
    )
}