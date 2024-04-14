package com.example.h4j4.user.ui.subsequentialComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.enumClasses.StateOfPortion
@Composable
fun Portion(portionSize: Int, suffix: String, newValueOfPortion: (Int) -> Unit) {

//    Portion
    var portion by remember { mutableStateOf(portionSize) }
    var stateOfPortion by remember {
        mutableStateOf(
            when (portion) {
                0 -> {
                    StateOfPortion.DoesNotExist
                }

                else -> {
                    StateOfPortion.Exists
                }
            }
        )
    }

//    IconButton responsible for adding, editing and saving the portion.
    var iconOfIconButton by remember { mutableStateOf(Icons.Rounded.Add) }
    var tintOfIconButton by remember { mutableStateOf(Color.White) }
    var iconButtonIsEnabled by remember { mutableStateOf(true) }

//    OutlinedTextField
    var displayOutlinedTextField by remember { mutableStateOf(false) }
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    var enableOutlinedTextField by remember { mutableStateOf(false) }

//    Dialog
    var displayDialog by remember { mutableStateOf(false) }

    LaunchedEffect(enableOutlinedTextField, displayOutlinedTextField, portion) {

//        Launching keyboard when the user clicks "add" icon
        if (enableOutlinedTextField && displayOutlinedTextField && portion == 0) {
            focusRequester.requestFocus()
        }

//        Launching keyboard when the user clicks "edit" icon
        if (stateOfPortion == StateOfPortion.IsBeingEdited) {
            focusRequester.requestFocus()
        }
    }

//    Changing appearance and states of the IconButtons and the OutlinedTextField
    LaunchedEffect(stateOfPortion, portion) {

        if (portion == 0 || stateOfPortion != StateOfPortion.DoesNotExist) {

            iconButtonIsEnabled = false
            tintOfIconButton = Color.Red
        }

        when (stateOfPortion) {
            StateOfPortion.DoesNotExist -> {
                iconOfIconButton = Icons.Rounded.Add
                tintOfIconButton = Color.White
                iconButtonIsEnabled = true
                displayOutlinedTextField = false
            }
            StateOfPortion.IsBeingCreated -> {
                iconOfIconButton = Icons.Rounded.Check
                tintOfIconButton = if (portion == 0) { Color.Red} else { Color.White}
                iconButtonIsEnabled = if (portion == 0) {false} else {true}
                displayOutlinedTextField = true
            }
            StateOfPortion.Exists -> {
                iconOfIconButton = Icons.Rounded.Edit
                tintOfIconButton = Color.White
                iconButtonIsEnabled = if (portion == 0) {false} else {true}
                displayOutlinedTextField = true
            }
            StateOfPortion.IsBeingEdited -> {
                iconOfIconButton = Icons.Rounded.Check
                tintOfIconButton = if (portion == 0) {Color.Red} else {Color.White}
                iconButtonIsEnabled = if (portion == 0) {false} else {true}
                displayOutlinedTextField = true
            }
        }
    }

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            MyIconButton(icon = iconOfIconButton, tint = tintOfIconButton, isClickable = iconButtonIsEnabled) {

                when (stateOfPortion) {
                    StateOfPortion.DoesNotExist -> { // icon add
                        stateOfPortion = StateOfPortion.IsBeingCreated
                        enableOutlinedTextField = true
                        displayOutlinedTextField = true
                    }
                    StateOfPortion.IsBeingCreated -> { // icon check
                        stateOfPortion = StateOfPortion.Exists
                        enableOutlinedTextField = false
                        focusRequester.freeFocus()
                        newValueOfPortion(portion)
                    }
                    StateOfPortion.Exists -> { // icon edit
                        stateOfPortion = StateOfPortion.IsBeingEdited
                        enableOutlinedTextField = true
                        focusRequester.requestFocus()
                    }

                    StateOfPortion.IsBeingEdited -> { // icon check
                        stateOfPortion = StateOfPortion.Exists
                        enableOutlinedTextField = false
                        focusRequester.freeFocus()
                        newValueOfPortion(portion)
                    }
                }
            }

            if (displayOutlinedTextField) {

                MyOutlinedTextield(value = portion, suffix = suffix, isEnabled = enableOutlinedTextField, isVisible = true, focusRequester = focusRequester) {
                    portion = it
                }

                MyIconButton(icon = Icons.Rounded.Delete, tint = Color.White, isClickable = true) {

                    when (stateOfPortion) {
                        StateOfPortion.DoesNotExist -> {}
                        StateOfPortion.IsBeingCreated -> {displayDialog = true}
                        StateOfPortion.Exists -> {displayDialog = true}
                        StateOfPortion.IsBeingEdited -> {displayDialog = true}
                    }
                }
            }

            if (displayDialog) {

                val descriptionOfTheDialog = when (stateOfPortion) {

                    StateOfPortion.Exists -> {"Are you sure you want to delete this portion?"}

                    StateOfPortion.IsBeingCreated -> {"Are you sure you want to cancel adding new portion?"}

                    StateOfPortion.DoesNotExist -> TODO()

                    StateOfPortion.IsBeingEdited -> {"Are you sure you want to delete changes for this portion?"}
                }

                MyAlertDialog(
                    text = descriptionOfTheDialog,

                    onDismissRequest = {
                        displayDialog = false

                        if (enableOutlinedTextField) {
                            enableOutlinedTextField = true
                        }
                    },

                    onClickConfirmButton = {
                        displayDialog = false

                        when (stateOfPortion) {
                            StateOfPortion.DoesNotExist -> {}
                            StateOfPortion.IsBeingCreated -> {
                                stateOfPortion = StateOfPortion.DoesNotExist
                                portion = 0
                                enableOutlinedTextField = false
                            }
                            StateOfPortion.IsBeingEdited -> {
                                stateOfPortion = StateOfPortion.Exists
                                enableOutlinedTextField = false
                            }
                            StateOfPortion.Exists -> {
                                stateOfPortion = StateOfPortion.DoesNotExist
                                portion = 0
                                newValueOfPortion(portion)
                                enableOutlinedTextField = false
                            }
                        }
                    },

                    onClickDismissButton = {
                        displayDialog = false

                        if (enableOutlinedTextField) {
                            enableOutlinedTextField = true
                        }
                    }
                )
            }
        }
    )
}