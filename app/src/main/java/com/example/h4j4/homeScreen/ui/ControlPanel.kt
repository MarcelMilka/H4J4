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
import com.example.h4j4.homeScreen.viewModel.HomeScreenViewModel
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

// TODO: is passing viewModel as a parameter into a composable a bad practice because of coupling programmatically a composable between another file ?
@Composable
fun ControllPanel(uiState: HomeScreenViewState, viewModel: HomeScreenViewModel) {

    val currentDayOfWeek= LocalDate.now().dayOfWeek.toString().lowercase()
    var waterIngestedToday = 0f
    var creatineIngestedToday = 0f

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

                            waterIngestedToday = if (uiState.whatIsTracked.water) {
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
                            creatineIngestedToday = if (uiState.whatIsTracked.creatine) {
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
                                AssistChipLoaded(uiState.portionsOfWater.firstPortion, waterIngestedToday.toInt()) {

                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModel.increaseAmountOfDrankWaterAndAddLog(it.toString(), uiState.portionsOfWater.firstPortion.toString())
                                    }
                                }
                                AssistChipLoaded(uiState.portionsOfWater.secondPortion, waterIngestedToday.toInt()) {

                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModel.increaseAmountOfDrankWaterAndAddLog(it.toString(), uiState.portionsOfWater.secondPortion.toString())
                                    }
                                }
                                AssistChipLoaded(uiState.portionsOfWater.thirdPortion, waterIngestedToday.toInt()) {

                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModel.increaseAmountOfDrankWaterAndAddLog(it.toString(), uiState.portionsOfWater.thirdPortion.toString())
                                    }
                                }
                            }

                            else {
                                AssistChipLoaded(0, 0) {}
                                AssistChipLoaded(0, 0) {}
                                AssistChipLoaded(0, 0) {}
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
                                AssistChipLoaded(uiState.portionsOfCreatine.firstPortion, creatineIngestedToday.toInt()) {
                                    // TODO: add a function for creatine
                                }
                                AssistChipLoaded(uiState.portionsOfCreatine.secondPortion, creatineIngestedToday.toInt()) {
                                    // TODO: add a function for creatine
                                }
                                AssistChipLoaded(uiState.portionsOfCreatine.thirdPortion, creatineIngestedToday.toInt()) {
                                    // TODO: add a function for creatine
                                }
                            }

                            else {
                                AssistChipLoaded(0, 0) {}
                                AssistChipLoaded(0, 0) {}
                                AssistChipLoaded(0, 0) {}
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
fun AssistChipLoaded(valueOfPortion: Int, currentAmountOfDrankWaterToday: Int, calculatedNewValue: (amountOfWaterDrankToday: Int) -> Unit) {

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
                calculatedNewValue(currentAmountOfDrankWaterToday + valueOfPortion)
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