package com.example.h4j4.user.ui.mainComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.enumClasses.StateOfPortion
import com.example.h4j4.user.ui.subsequentialComponents.MyAlertDialog
import com.example.h4j4.user.ui.subsequentialComponents.MyIconButton
import com.example.h4j4.user.ui.subsequentialComponents.MyOutlinedTextield
import com.example.h4j4.user.ui.subsequentialComponents.Portion
import com.example.h4j4.user.viewState.UserViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MainContent(uiState: UserViewState) {

//    var waterIsTracked by remember { mutableStateOf(false) }

    var heightOfWaterSurface by remember { mutableStateOf(50) }

//    = when (waterIsTracked) {
//        true -> {
//            220.dp
//        }
//
//        false -> {
//            50.dp
//        }
//    }

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
                    var waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }

                    var firstPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.firstPortion) }
                    var secondPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.secondPortion) }
                    var thirdPortionOfWater by remember { mutableStateOf(uiState.portionsOfWater.thirdPortion) }

                    LaunchedEffect(waterIsTracked) {

                        when (waterIsTracked) {
                            true -> {
                                heightOfWaterSurface = 220
                            }

                            false -> {
                                heightOfWaterSurface = 50
                            }
                        }
                    }

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightOfWaterSurface.dp)
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

                                Row(
                                    Modifier.fillMaxWidth(),

                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,

                                    content = {
                                        Text(text ="     Daily amount of water to drink:", color = Color.White)
                                        TextButton(
                                            onClick = {},
                                            content = {
                                                Text(text = "${uiState.dailyAmountOfWaterToIngest.dailyAmountOfWaterToIngest} ml", color = Color.White)
                                                Icon(modifier = Modifier.size(15.dp), imageVector = Icons.Rounded.Edit, contentDescription = null, tint = Color.White)
                                            }
                                        )
                                    }
                                )

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