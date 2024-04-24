package com.example.h4j4.user.ui.mainComponents

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
                          var defaultWaterIsTrackedAsBoolean by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var defaultWaterIsTrackedAsInt by remember {
                              mutableStateOf(
                                  when (defaultWaterIsTrackedAsBoolean) {
                                      true -> { 1 }

                                      false -> { 0 }
                                  }
                              )
                          }

                          var defaultDailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }
                          var defaultFirstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var defaultSecondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var defaultThirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                        Editable values
                          var waterIsTrackedAsBoolean by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var waterIsTrackedAsInt by remember {
                              mutableStateOf(
                                  when (waterIsTrackedAsBoolean) {
                                      true -> { 1 }

                                      false -> { 0 }
                                  }
                              )
                          }

                          var dailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }
                          var firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                        Adjust height of the water fragment
                          LaunchedEffect(defaultWaterIsTrackedAsBoolean, secondPortionOfWater) {

                              when (defaultWaterIsTrackedAsBoolean) {
                                  true -> { heightOfWaterSurface = if (secondPortionOfWater == 0) { 220 } else { 260 } }
                                  false -> { heightOfWaterSurface = 50 }
                              }
                          }

//                    Creatine

//                        Default values
                          var defaultCreatineIsTrackedAsBoolean by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var defaultCreatineIsTrackedAsInt by remember {
                              mutableStateOf(
                                  when (defaultCreatineIsTrackedAsBoolean) {
                                      true -> { 1 }

                                      false -> { 0 }
                                  }
                              )
                          }

                          var defaultDailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }
                          var defaultFirstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var defaultSecondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var defaultThirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }


//                        Editable values
                          var creatineIsTrackedAsBoolean by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var creatineIsTrackedAsInt by remember {
                              mutableStateOf(
                                  when (creatineIsTrackedAsBoolean) {
                                      true -> { 1 }

                                      false -> { 0 }
                                  }
                              )
                          }

                          var dailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }
                          var firstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var secondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var thirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }

//                        Adjust height of the creatine fragment
                          LaunchedEffect(creatineIsTrackedAsBoolean, secondPortionOfCreatine) {
                              when (creatineIsTrackedAsBoolean) {
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
                        ValuesToCompare(defaultValue = defaultWaterIsTrackedAsInt, editableValue = waterIsTrackedAsInt, nameOfSubcollection = "What is tracked", nameOfField = "water"),
                        ValuesToCompare(defaultValue = defaultDailyAmountOfWaterToIngest, editableValue = dailyAmountOfWaterToIngest, nameOfSubcollection = "Weekly intake of water", nameOfField = "daily goal"),
                        ValuesToCompare(defaultValue = defaultFirstPortionOfWater, editableValue = firstPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = defaultSecondPortionOfWater, editableValue = secondPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = defaultThirdPortionOfWater, editableValue = thirdPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "third portion"),

                        ValuesToCompare(defaultValue = defaultCreatineIsTrackedAsInt, editableValue = creatineIsTrackedAsInt, nameOfSubcollection = "What is tracked", nameOfField = "creatine"),
                        ValuesToCompare(defaultValue = defaultDailyAmountOfCreatineToIngest, editableValue = dailyAmountOfCreatineToIngest, nameOfSubcollection = "Weekly intake of creatine", nameOfField = "daily goal"),
                        ValuesToCompare(defaultValue = defaultFirstPortionOfCreatine, editableValue = firstPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = defaultSecondPortionOfCreatine, editableValue = secondPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = defaultThirdPortionOfCreatine, editableValue = thirdPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "third portion")
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
                        isTracked = waterIsTrackedAsBoolean,
                        subsequentSubstanceIsTracked = creatineIsTrackedAsBoolean,
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
                        onIsTrackedChanged = { waterIsTrackedAsBoolean = it },
                        onFirstPortionChanged = {firstPortionOfWater = it},
                        onSecondPortionChanged = {secondPortionOfWater = it},
                        onThirdPortionChanged = { thirdPortionOfWater = it }
                    )

                    MyFragment(
                        heightOfSurface = heightOfCreatineSurface,
                        isTracked = creatineIsTrackedAsBoolean,
                        subsequentSubstanceIsTracked = waterIsTrackedAsBoolean,
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
                        onIsTrackedChanged = { creatineIsTrackedAsBoolean = it },
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