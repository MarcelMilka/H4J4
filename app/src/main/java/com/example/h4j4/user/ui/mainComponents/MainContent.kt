package com.example.h4j4.user.ui.mainComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.CheckIfSomethingHasChanged
import com.example.h4j4.user.ui.subsequentialComponents.MyFragment
import com.example.h4j4.user.viewState.UserViewState

@Composable
fun ColumnScope.MainContent(uiState: UserViewState) {

    var heightOfWaterSurface by remember { mutableStateOf(50) }
    var heightOfCreatineSurface by remember { mutableStateOf(50) }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .weight(21f, true),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,

        content = {

            when (uiState) {

                UserViewState.Loading -> {
                    Text(text = "Water", color = Color.White)
                }

                is UserViewState.LoadedSuccessfully -> {

                    var changesOccurred by remember { mutableStateOf(false) }

                    val checkIfSomethingHasChanged = CheckIfSomethingHasChanged(
                        waterIsTracked = uiState.whatIsTracked.water,
                        creatineIsTracked = uiState.whatIsTracked.creatine,

                        dailyGoalOfIngestionOfWater = uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt(),
                        dailyGoalOfIngestionOfCreatine = uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt(),

                        firstPortionOfWater = uiState.portionsOfWater.firstPortion,
                        secondPortionOfWater = uiState.portionsOfWater.secondPortion,
                        thirdPortionOfWater = uiState.portionsOfWater.thirdPortion,

                        thirdPortionOfCreatine = uiState.portionsOfCreatine.firstPortion,
                        firstPortionOfCreatine = uiState.portionsOfCreatine.secondPortion,
                        secondPortionOfCreatine = uiState.portionsOfCreatine.thirdPortion,
                    )

                    var changes by remember { mutableStateOf(
                        CheckIfSomethingHasChanged(
                            waterIsTracked = uiState.whatIsTracked.water,
                            creatineIsTracked = uiState.whatIsTracked.creatine,

                            dailyGoalOfIngestionOfWater = uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt(),
                            dailyGoalOfIngestionOfCreatine = uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt(),

                            firstPortionOfWater = uiState.portionsOfWater.firstPortion,
                            secondPortionOfWater = uiState.portionsOfWater.secondPortion,
                            thirdPortionOfWater = uiState.portionsOfWater.thirdPortion,

                            thirdPortionOfCreatine = uiState.portionsOfCreatine.firstPortion,
                            firstPortionOfCreatine = uiState.portionsOfCreatine.secondPortion,
                            secondPortionOfCreatine = uiState.portionsOfCreatine.thirdPortion,
                        )
                    ) }

                    LaunchedEffect(checkIfSomethingHasChanged, changes) {

                        if (checkIfSomethingHasChanged != changes) {
                            changesOccurred = true
                        }

                        else {
                            changesOccurred = false
                        }
                    }

//                    Water
                    var waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }
                    var dailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }

                    var firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                    var secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                    var thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

                    LaunchedEffect(waterIsTracked, secondPortionOfWater) {

                        when (waterIsTracked) {
                            true -> {

                                if (secondPortionOfWater == 0) {

                                    heightOfWaterSurface = 220
                                }

                                else {

                                    heightOfWaterSurface = 260
                                }
                            }

                            false -> {
                                heightOfWaterSurface = 50
                            }
                        }
                    }

//                    Creatine
                    var creatineIsTracked by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                    var dailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }

                    var firstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                    var secondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                    var thirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }

                    LaunchedEffect(creatineIsTracked, secondPortionOfCreatine) {

                        when (creatineIsTracked) {
                            true -> {

                                if (secondPortionOfCreatine == 0) {

                                    heightOfCreatineSurface = 220
                                }

                                else {

                                    heightOfCreatineSurface = 260
                                }
                            }

                            false -> {
                                heightOfCreatineSurface = 50
                            }
                        }
                    }

                    MyFragment(
                        heightOfSurface = heightOfWaterSurface,
                        isTracked = checkIfSomethingHasChanged.waterIsTracked,
                        dailyGoal = checkIfSomethingHasChanged.dailyGoalOfIngestionOfWater,
                        suffix = "ml",
                        waterOrCreatine = "Water",
                        firstPortion = checkIfSomethingHasChanged.firstPortionOfWater,
                        secondPortion = checkIfSomethingHasChanged.secondPortionOfWater,
                        thirdPortion = checkIfSomethingHasChanged.thirdPortionOfWater,
                        onIsTrackedChanged = { changes.waterIsTracked = it },
                        onFirstPortionChanged = {changes.firstPortionOfWater = it},
                        onSecondPortionChanged = {changes.secondPortionOfWater = it},
                        onThirdPortionChanged = { changes.thirdPortionOfWater = it }
                    )

                    Spacer(Modifier.height(50.dp))

                    MyFragment(
                        heightOfSurface = heightOfCreatineSurface,
                        isTracked = checkIfSomethingHasChanged.creatineIsTracked,
                        dailyGoal = checkIfSomethingHasChanged.dailyGoalOfIngestionOfCreatine,
                        suffix = "g",
                        waterOrCreatine = "Creatine",
                        firstPortion = checkIfSomethingHasChanged.firstPortionOfCreatine,
                        secondPortion = checkIfSomethingHasChanged.secondPortionOfCreatine,
                        thirdPortion = checkIfSomethingHasChanged.thirdPortionOfCreatine,
                        onIsTrackedChanged = { changes.creatineIsTracked = it },
                        onFirstPortionChanged = {changes.firstPortionOfCreatine = it},
                        onSecondPortionChanged = {changes.secondPortionOfCreatine = it},
                        onThirdPortionChanged = { changes.thirdPortionOfCreatine = it }
                    )
                }
            }
        }
    )
}