package com.example.h4j4.user.ui.subsequentialComponents

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
fun Portion(portionSize: Int, newValueOfPortion: (Int) -> Unit) {

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
    var icon by remember { mutableStateOf(Icons.Rounded.Add) }

    var tint by remember { mutableStateOf(Color.White) }

    var isClickable by remember { mutableStateOf(true) }

//    OutlinedTextField
    var displayOutlinedTextField by remember { mutableStateOf(false) }
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isEnabled by remember { mutableStateOf(false) }


//    Dialog
    var displayDialog by remember { mutableStateOf(false) }

//    Operator
    LaunchedEffect(stateOfPortion, portion) {

        if (portion == 0 || stateOfPortion != StateOfPortion.DoesNotExist) {

            isClickable = false
            tint = Color.Red
        }

        when (stateOfPortion) {
            StateOfPortion.DoesNotExist -> {
                icon = Icons.Rounded.Add
                tint = Color.White
                isClickable = true
                displayOutlinedTextField = false
            }
            StateOfPortion.IsBeingCreated -> {
                icon = Icons.Rounded.Check
                tint = if (portion == 0) {
                    Color.Red} else {
                    Color.White}
                isClickable = if (portion == 0) {false} else {true}
                displayOutlinedTextField = true
            }
            StateOfPortion.Exists -> {
                icon = Icons.Rounded.Edit
                tint = Color.White
                isClickable = if (portion == 0) {false} else {true}
                displayOutlinedTextField = true
            }
            StateOfPortion.IsBeingEdited -> {
                icon = Icons.Rounded.Check
                tint = if (portion == 0) {
                    Color.Red} else {
                    Color.White}
                isClickable = if (portion == 0) {false} else {true}
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

            MyIconButton(icon = icon, tint = tint, isClickable = isClickable) {

                when (stateOfPortion) {
                    StateOfPortion.DoesNotExist -> { // icon add
                        stateOfPortion = StateOfPortion.IsBeingCreated
                    }
                    StateOfPortion.IsBeingCreated -> { // icon check
                        stateOfPortion = StateOfPortion.Exists
                    }
                    StateOfPortion.Exists -> { // icon edit
                        stateOfPortion = StateOfPortion.IsBeingEdited
                    }

                    StateOfPortion.IsBeingEdited -> { // icon check
                        stateOfPortion = StateOfPortion.Exists
                    }
                }

            }

            if (displayOutlinedTextField) {

                MyOutlinedTextield(value = portion, suffix = "ml", isEnabled = isEnabled, isVisible = true, focusRequester = focusRequester) {
                    portion = it
                }

                MyIconButton(icon = Icons.Rounded.Delete, tint = Color.White, isClickable = true) {

                    when (stateOfPortion) {
                        StateOfPortion.DoesNotExist -> {}
                        StateOfPortion.IsBeingCreated -> {
                            displayDialog = true
                        }
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

                    onDismissRequest = {displayDialog = false},

                    onClickConfirmButton = {
                        displayDialog = false

                        when (stateOfPortion) {
                            StateOfPortion.DoesNotExist -> {}
                            StateOfPortion.IsBeingCreated -> {stateOfPortion = StateOfPortion.DoesNotExist}
                            StateOfPortion.IsBeingEdited -> {stateOfPortion = StateOfPortion.Exists}
                            StateOfPortion.Exists -> {stateOfPortion = StateOfPortion.DoesNotExist}
                        }
                    },

                    onClickDismissButton = {displayDialog = false}
                )
            }
        }
    )
}