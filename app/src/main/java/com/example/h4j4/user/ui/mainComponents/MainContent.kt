package com.example.h4j4.user.ui.mainComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.user.ui.subsequentialComponents.MyFragment
import com.example.h4j4.user.userModalBottomSheet.ui.UserModalBottomSheet
import com.example.h4j4.user.viewState.UserViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MainContent(
    uiState: UserViewState,
    onTextButtonSaveChanges: (Boolean) -> Unit
) {

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

//                    Water

//                        Default values
                          var _waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var _dailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }

                          var _firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var _secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var _thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                        Editable values
                          var waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var dailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }

                          var firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                  Adjust height of the water fragment
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

//                        Default values
                          var _creatineIsTracked by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var _dailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }

                          var _firstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var _secondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var _thirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }


//                        Editable values
                          var creatineIsTracked by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var dailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }

                          var firstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var secondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var thirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }

//                  Adjust height of the creatine fragment
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

//                  ModalBottomSheet
                    var displayModalBottomSheet by remember { mutableStateOf(false) }
                    var modalBottomSheetRepresents: WaterOrCreatine? by remember { mutableStateOf(null) }
                    var currentDailyGoal by remember { mutableStateOf(0) }
                    val sheetState = rememberModalBottomSheetState()

                    LaunchedEffect(
                        _waterIsTracked, waterIsTracked,
                        _creatineIsTracked, creatineIsTracked,

                        _dailyAmountOfWaterToIngest, dailyAmountOfWaterToIngest,
                        _dailyAmountOfCreatineToIngest, dailyAmountOfCreatineToIngest,

                        _firstPortionOfWater, firstPortionOfWater,
                        _firstPortionOfCreatine, firstPortionOfCreatine,

                        _secondPortionOfWater, secondPortionOfWater,
                        _secondPortionOfCreatine, secondPortionOfCreatine,

                        _thirdPortionOfWater, thirdPortionOfWater,
                        _thirdPortionOfCreatine, thirdPortionOfCreatine

                    ) {

                        if (_waterIsTracked != waterIsTracked || _creatineIsTracked != creatineIsTracked) { onTextButtonSaveChanges(true) }

                        else if (_dailyAmountOfWaterToIngest != dailyAmountOfWaterToIngest || _dailyAmountOfCreatineToIngest != dailyAmountOfCreatineToIngest) { onTextButtonSaveChanges(true) }

                        else if (_firstPortionOfWater != firstPortionOfWater ||  _firstPortionOfCreatine != firstPortionOfCreatine) { onTextButtonSaveChanges(true) }

                        else if (_secondPortionOfWater != secondPortionOfWater || _secondPortionOfCreatine != secondPortionOfCreatine) { onTextButtonSaveChanges(true) }

                        else if (_thirdPortionOfWater != thirdPortionOfWater || _thirdPortionOfCreatine != thirdPortionOfCreatine) { onTextButtonSaveChanges(true) }

                        else { onTextButtonSaveChanges(false) }
                    }

                    MyFragment(
                        heightOfSurface = heightOfWaterSurface,
                        isTracked = waterIsTracked,
                        subsequentSubstanceIsTracked = creatineIsTracked,
                        dailyGoal = dailyAmountOfWaterToIngest,
                        suffix = "ml",
                        waterOrCreatine = "Water",
                        firstPortion = firstPortionOfWater,
                        secondPortion = secondPortionOfWater,
                        thirdPortion = thirdPortionOfWater,
                        displayModalBottomSheet = {
                            displayModalBottomSheet = true
                            modalBottomSheetRepresents = WaterOrCreatine.WATER
                            currentDailyGoal = dailyAmountOfWaterToIngest
                        },
                        onIsTrackedChanged = { waterIsTracked = it },
                        onFirstPortionChanged = {firstPortionOfWater = it},
                        onSecondPortionChanged = {secondPortionOfWater = it},
                        onThirdPortionChanged = { thirdPortionOfWater = it }
                    )

                    Spacer(Modifier.height(50.dp))

                    MyFragment(
                        heightOfSurface = heightOfCreatineSurface,
                        isTracked = creatineIsTracked,
                        subsequentSubstanceIsTracked = waterIsTracked,
                        dailyGoal = dailyAmountOfCreatineToIngest,
                        suffix = "g",
                        waterOrCreatine = "Creatine",
                        firstPortion = firstPortionOfCreatine,
                        secondPortion = secondPortionOfCreatine,
                        thirdPortion = thirdPortionOfCreatine,
                        displayModalBottomSheet = {
                            displayModalBottomSheet = true
                            modalBottomSheetRepresents = WaterOrCreatine.CREATINE
                            currentDailyGoal = dailyAmountOfCreatineToIngest
                        },
                        onIsTrackedChanged = { creatineIsTracked = it },
                        onFirstPortionChanged = {firstPortionOfCreatine = it},
                        onSecondPortionChanged = {secondPortionOfCreatine = it},
                        onThirdPortionChanged = { thirdPortionOfCreatine = it }
                    )

                    UserModalBottomSheet(
                        displayModalBottomSheet = displayModalBottomSheet,
                        modalBottomSheetRepresents = modalBottomSheetRepresents,
                        currentDailyGoal = currentDailyGoal,
                        sheetState = sheetState,
                    ) {
                        when (modalBottomSheetRepresents) {
                            WaterOrCreatine.WATER -> { dailyAmountOfWaterToIngest = it }
                            WaterOrCreatine.CREATINE -> { dailyAmountOfCreatineToIngest = it }
                            null -> {}
                        }
                        displayModalBottomSheet = false
                        modalBottomSheetRepresents = null
                    }
                }
            }
        }
    )
}