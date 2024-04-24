package com.example.h4j4.user.ui.mainComponents

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.user.dataClasses.UserSettingsChange
import com.example.h4j4.user.ui.subsequentialComponents.MyFragment
import com.example.h4j4.user.userModalBottomSheet.ui.UserModalBottomSheet
import com.example.h4j4.user.viewState.UserViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MainContent(
    uiState: UserViewState,
    passChanges: (MutableList<UserSettingsChange>) -> Unit
) {

//  adjust height of the components to amount of existing portions
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

//                        Adjust height of the water fragment
                          LaunchedEffect(waterIsTracked, secondPortionOfWater) {

                              when (waterIsTracked) {
                                  true -> { heightOfWaterSurface = if (secondPortionOfWater == 0) { 220 } else { 260 } }
                                  false -> { heightOfWaterSurface = 50 }
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

//                        Adjust height of the creatine fragment
                          LaunchedEffect(creatineIsTracked, secondPortionOfCreatine) {
                              when (creatineIsTracked) {
                                  true -> { if (secondPortionOfCreatine == 0) { heightOfCreatineSurface = 220 } else { heightOfCreatineSurface = 260 } }
                                  false -> { heightOfCreatineSurface = 50 }
                              }
                          }

//                  ModalBottomSheet
                    var displayModalBottomSheet by remember { mutableStateOf(false) }
                    var modalBottomSheetRepresents: WaterOrCreatine? by remember { mutableStateOf(null) }
                    var currentDailyGoal by remember { mutableStateOf(0) }
                    val sheetState = rememberModalBottomSheetState()

//                  Enable button responsible for saving, send mutableList of applied changes to the button.

                    var listOfChanges by remember { mutableStateOf(mutableListOf<UserSettingsChange>()) }

                    var compareValues = mutableListOf(
                        ValuesToCompare(defaultValue = _firstPortionOfWater, editableValue = firstPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = _secondPortionOfWater, editableValue = secondPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = _thirdPortionOfWater, editableValue = thirdPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "third portion"),

                        ValuesToCompare(defaultValue = _firstPortionOfCreatine, editableValue = firstPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = _secondPortionOfCreatine, editableValue = secondPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = _thirdPortionOfCreatine, editableValue = thirdPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "third portion")
                    )

                    LaunchedEffect(compareValues) {

                        val changesToRemove = mutableListOf<UserSettingsChange>()
                        val changesToAdd = mutableListOf<UserSettingsChange>()

                        compareValues.forEach {

                            if (it.defaultValue != it.editableValue) {
                                val change = UserSettingsChange(nameOfSubcollection = it.nameOfSubcollection, nameOfField = it.nameOfField, newValue = it.editableValue)

                                if (listOfChanges.isEmpty()) {listOfChanges.add(change)}

                                else {

                                    for (existingChange in listOfChanges) {

//                                      check if the current subcollection and field already exist
                                        if (change.nameOfSubcollection == existingChange.nameOfSubcollection && change.nameOfField == existingChange.nameOfField) {

                                            changesToRemove.add(existingChange)
                                            changesToAdd.add(change)
                                        }

                                        else { changesToAdd.add(change) }
                                    }
                                }
                            }
                        }

                        listOfChanges.removeAll(changesToRemove)

                        changesToAdd.forEach {
                            if (!listOfChanges.contains(it)) {
                                listOfChanges.add(it)
                            }
                        }

                        passChanges(listOfChanges)
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

//                    Bottom sheet to change daily amount of water/creatine
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
data class ValuesToCompare (val defaultValue: Int = 0, val editableValue: Int = 0, val nameOfSubcollection: String, val nameOfField: String)