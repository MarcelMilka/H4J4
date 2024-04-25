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
                          var defaultWaterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var defaultDailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }
                          var defaultFirstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var defaultSecondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var defaultThirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                        Editable values
                          var editableWaterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }
                          var editableDailyAmountOfWaterToIngest by remember { mutableStateOf(uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest.toInt()) }
                          var editableFirstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                          var editableSecondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                          var editableThirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                        Adjust height of the water fragment
                          LaunchedEffect(editableWaterIsTracked, editableSecondPortionOfWater) {

                              when (editableWaterIsTracked) {
                                  true -> { heightOfWaterSurface = if (editableSecondPortionOfWater == 0) { 220 } else { 260 } }
                                  false -> { heightOfWaterSurface = 50 }
                              }
                          }

//                    Creatine

//                        Default values
                          var defaultCreatineIsTracked by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var defaultDailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }
                          var defaultFirstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var defaultSecondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var defaultThirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }


//                        Editable values
                          var editableCreatineIsTracked by remember { mutableStateOf(uiState.whatIsTracked.creatine) }
                          var editableDailyAmountOfCreatineToIngest by remember { mutableStateOf(uiState.dailyAmountOfCreatineToIngest.dailyAmountOfCreatineToIngest.toInt()) }
                          var editableFirstPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.firstPortion) }
                          var editableSecondPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.secondPortion) }
                          var editableThirdPortionOfCreatine by remember { mutableStateOf(uiState.portionsOfCreatine.thirdPortion) }

//                        Adjust height of the creatine fragment
                          LaunchedEffect(editableCreatineIsTracked, editableSecondPortionOfCreatine) {
                              when (editableCreatineIsTracked) {
                                  true -> { if (editableSecondPortionOfCreatine == 0) { heightOfCreatineSurface = 220 } else { heightOfCreatineSurface = 260 } }
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
                        ValuesToCompare(defaultValue = 0, editableValue = 0, nameOfSubcollection = "What is tracked", nameOfField = "water").apply {

                            when (defaultWaterIsTracked) {
                                true -> {
                                    this.defaultValue = 1
                                }

                                false -> {
                                    this.defaultValue = 0
                                }
                            }

                            when (editableWaterIsTracked) {
                                true -> {
                                    this.editableValue = 1
                                }

                                false -> {
                                    this.editableValue = 0
                                }
                            }
                        },
                        ValuesToCompare(defaultValue = defaultDailyAmountOfWaterToIngest, editableValue = editableDailyAmountOfWaterToIngest, nameOfSubcollection = "Weekly intake of water", nameOfField = "daily goal"),

                        ValuesToCompare(defaultValue = defaultFirstPortionOfWater, editableValue = editableFirstPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = defaultSecondPortionOfWater, editableValue = editableSecondPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = defaultThirdPortionOfWater, editableValue = editableThirdPortionOfWater, nameOfSubcollection = "Portions of water", nameOfField = "third portion"),

                        ValuesToCompare(defaultValue = 0, editableValue = 0, nameOfSubcollection = "What is tracked", nameOfField = "creatine").apply {

                            when (defaultCreatineIsTracked) {
                                true -> {
                                    this.defaultValue = 1
                                }

                                false -> {
                                    this.defaultValue = 0
                                }
                            }

                            when (editableCreatineIsTracked) {
                                true -> {
                                    this.editableValue = 1
                                }

                                false -> {
                                    this.editableValue = 0
                                }
                            }
                        },
                        ValuesToCompare(defaultValue = defaultDailyAmountOfCreatineToIngest, editableValue = editableDailyAmountOfCreatineToIngest, nameOfSubcollection = "Weekly intake of creatine", nameOfField = "daily goal"),
                        ValuesToCompare(defaultValue = defaultFirstPortionOfCreatine, editableValue = editableFirstPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "first portion"),
                        ValuesToCompare(defaultValue = defaultSecondPortionOfCreatine, editableValue = editableSecondPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "second portion"),
                        ValuesToCompare(defaultValue = defaultThirdPortionOfCreatine, editableValue = editableThirdPortionOfCreatine, nameOfSubcollection = "Portions of creatine", nameOfField = "third portion")
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
                        isTracked = editableWaterIsTracked,
                        subsequentSubstanceIsTracked = editableCreatineIsTracked,
                        dailyGoal = editableDailyAmountOfWaterToIngest,
                        suffix = "ml",
                        waterOrCreatine = "Water",
                        firstPortion = editableFirstPortionOfWater,
                        secondPortion = editableSecondPortionOfWater,
                        thirdPortion = editableThirdPortionOfWater,
                        displayModalBottomSheet = {
                            displayModalBottomSheet = true
                            modalBottomSheetRepresents = WaterOrCreatine.WATER
                            currentDailyGoal = editableDailyAmountOfWaterToIngest
                        },
                        onIsTrackedChanged = { editableWaterIsTracked = it },
                        onFirstPortionChanged = {editableFirstPortionOfWater = it},
                        onSecondPortionChanged = {editableSecondPortionOfWater = it},
                        onThirdPortionChanged = { editableThirdPortionOfWater = it }
                    )

                    MyFragment(
                        heightOfSurface = heightOfCreatineSurface,
                        isTracked = editableCreatineIsTracked,
                        subsequentSubstanceIsTracked = editableWaterIsTracked,
                        dailyGoal = editableDailyAmountOfCreatineToIngest,
                        suffix = "g",
                        waterOrCreatine = "Creatine",
                        firstPortion = editableFirstPortionOfCreatine,
                        secondPortion = editableSecondPortionOfCreatine,
                        thirdPortion = editableThirdPortionOfCreatine,
                        displayModalBottomSheet = {
                            displayModalBottomSheet = true
                            modalBottomSheetRepresents = WaterOrCreatine.CREATINE
                            currentDailyGoal = editableDailyAmountOfCreatineToIngest
                        },
                        onIsTrackedChanged = { editableCreatineIsTracked = it },
                        onFirstPortionChanged = {editableFirstPortionOfCreatine = it},
                        onSecondPortionChanged = {editableSecondPortionOfCreatine = it},
                        onThirdPortionChanged = { editableThirdPortionOfCreatine = it }
                    )

//                    Bottom sheet to change daily amount of water/creatine
                    UserModalBottomSheet(
                        displayModalBottomSheet = displayModalBottomSheet,
                        modalBottomSheetRepresents = modalBottomSheetRepresents,
                        currentDailyGoal = currentDailyGoal,
                        sheetState = sheetState,
                    ) {
                        when (modalBottomSheetRepresents) {
                            WaterOrCreatine.WATER -> { editableDailyAmountOfWaterToIngest = it }
                            WaterOrCreatine.CREATINE -> { editableDailyAmountOfCreatineToIngest = it }
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
data class ValuesToCompare (var defaultValue: Int = 0, var editableValue: Int = 0, val nameOfSubcollection: String, val nameOfField: String)