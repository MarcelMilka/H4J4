package com.example.h4j4.user.ui.mainComponents

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.enumClasses.StateOfPortion
import com.example.h4j4.user.ui.subsequentialComponents.MyAlertDialog
import com.example.h4j4.user.ui.subsequentialComponents.MyIconButton
import com.example.h4j4.user.ui.subsequentialComponents.MyOutlinedTextield
import com.example.h4j4.user.viewState.UserViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MainContent(uiState: UserViewState) {

//    Water
    var waterIsTracked by remember { mutableStateOf(false) }

//    var firstPortionOfWater by remember { mutableStateOf(0) }
//    var secondPortionOfWater by remember { mutableStateOf(0) }
//    var thirdPortionOfWater by remember { mutableStateOf(0) }

    var heightOfWaterSurface = when (waterIsTracked) {
        true -> {
            220.dp
        }

        false -> {
            50.dp
        }
    }

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

//                    Filling data
                    var firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                    var secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                    var thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

//                    Water
                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightOfWaterSurface)
                            .border(
                                width = 4.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(start = 10.dp),

                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,

                        content = {

                            FilterChip(

                                selected = true,
                                onClick = { waterIsTracked = !waterIsTracked },
                                label = { Text(text = "Water", color = Color.White) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color.Transparent,

                                ),
                                trailingIcon = {
                                    if (waterIsTracked) {
                                        androidx.compose.material.Icon(
                                            imageVector = Icons.Rounded.Check,
                                            tint = Color.White,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )

                            if (waterIsTracked) {

                                Portion(firstPortionOfWater) {newValueOfPortion: Int -> firstPortionOfWater = newValueOfPortion}

                                if (firstPortionOfWater != 0) {

                                    Portion(secondPortionOfWater) {newValueOfPortion: Int -> secondPortionOfWater = newValueOfPortion}
                                }

                                if (secondPortionOfWater != 0) {

                                    Portion(thirdPortionOfWater) {newValueOfPortion: Int -> thirdPortionOfWater = newValueOfPortion}
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun Portion(portionSize: Int, newValueOfPortion: (Int) -> Unit) {

//    Portion
    var portion by remember {mutableStateOf(portionSize)}
    var stateOfPortion by remember {

        mutableStateOf(

            when (portionSize) {

                0 -> {
                    StateOfPortion.DoesNotExist
                }

                else -> {
                    StateOfPortion.Exists
                }
            }
        )
    }

//    First IconButton
    var iconOfFirstIconButton by remember {

        mutableStateOf(
            when (portion) {

                0 -> {Icons.Rounded.Add}

                else -> {Icons.Rounded.Edit}
            }
        )

    }
    var tintOfFirstIconButton by remember { mutableStateOf(Color.White) }
    var firstIconButtonIsClickable by remember { mutableStateOf(true) }

//    OutlinedTextField
    var isReadOnly by remember { mutableStateOf(true) }
    var isVisible by remember {
        mutableStateOf(

            when (portion) {

                0 -> {false}

                else -> {true}
            }
        )
    }

//    Second IconButton
    var tintOfSecondIconButton by remember { mutableStateOf(Color.Transparent) }
    var secondIconButtonIsClickable by remember { mutableStateOf(false) }

//    Dialog
    var displayDialog by remember { mutableStateOf(false) }
    var dismissed: Boolean? by remember { mutableStateOf(null) }
    var confirmed: Boolean? by remember { mutableStateOf(null) }


    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

//            IconButton responsible for adding/editing the portion
            MyIconButton(iconOfFirstIconButton, tintOfFirstIconButton, firstIconButtonIsClickable) {

                when (stateOfPortion) {

                    StateOfPortion.DoesNotExist -> {

                        stateOfPortion = StateOfPortion.IsBeingCreated

//                        LeftIconButton
                        iconOfFirstIconButton = Icons.Rounded.Check

//                        OutlinedTextField
                        isVisible = true
                        isReadOnly = false

//                        RightIconButton
                        tintOfSecondIconButton = Color.White
                        secondIconButtonIsClickable = true

                    }

                    StateOfPortion.IsBeingCreated -> {}

                    StateOfPortion.Exists -> {}

                    StateOfPortion.IsBeingEdited -> {}
                }
            }

            MyOutlinedTextield(value = portion, suffix = "ml", readOnly = isReadOnly, isVisible = isVisible) {changedValue ->
                portion = changedValue
            }

//            IconButton responsible for deleting permanently portion / deleting changes
            MyIconButton(Icons.Rounded.Delete, tintOfSecondIconButton, secondIconButtonIsClickable) {

                when (stateOfPortion) {

                    StateOfPortion.DoesNotExist -> {}

                    StateOfPortion.IsBeingCreated -> {
                        displayDialog = true
                    }

                    StateOfPortion.Exists -> TODO()

                    StateOfPortion.IsBeingEdited -> TODO()

                }
            }

            if (displayDialog) {

                val descriptionOfTheDialog = when (stateOfPortion) {

                    StateOfPortion.Exists -> {"Are you sure you want to delete this portion?"}

                    StateOfPortion.IsBeingCreated -> {"Are you sure you want to cancel adding new portion?"}

                    StateOfPortion.IsBeingEdited -> {"Are you sure you want to delete changes for this portion?"}

                    StateOfPortion.DoesNotExist -> TODO()
                }

                MyAlertDialog(
                    text = descriptionOfTheDialog,

                    onDismissRequest = {
                        dismissed = true
                        confirmed = false

                        displayDialog = false
                    },

                    onClickConfirmButton = {
                        dismissed = true
                        confirmed = false

//                        Overall settings
                        stateOfPortion = StateOfPortion.DoesNotExist

//                        Left icon
                        iconOfFirstIconButton = Icons.Rounded.Add

//                        OutlinedTextField
                        isVisible = false

//                        Right icon
                        tintOfSecondIconButton = Color.Transparent
                        secondIconButtonIsClickable = false

//                        Hide dialog
                        displayDialog = false
                    },

                    onClickDismissButton = {

                        dismissed = true
                        confirmed = false

                        displayDialog = false
                    }
                )
            }
        }
    )
}