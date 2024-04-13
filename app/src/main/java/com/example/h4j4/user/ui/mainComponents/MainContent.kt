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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h4j4.user.ui.subsequentialComponents.MyFilterChip
import com.example.h4j4.user.ui.subsequentialComponents.Portion
import com.example.h4j4.user.viewState.UserViewState

@OptIn(ExperimentalMaterial3Api::class)
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

//                    Water
                    var waterIsTracked by remember { mutableStateOf(uiState.whatIsTracked.water) }

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

                            MyFilterChip(isTracked = waterIsTracked, text = "Water") {
                                waterIsTracked = it
                            }
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

                                Portion(firstPortionOfWater, "ml") {newValueOfPortion: Int -> firstPortionOfWater = newValueOfPortion}

                                if (firstPortionOfWater != 0) {

                                    Portion(secondPortionOfWater, "ml") {newValueOfPortion: Int -> secondPortionOfWater = newValueOfPortion}
                                }

                                if (secondPortionOfWater != 0) {

                                    Portion(thirdPortionOfWater, "ml") {newValueOfPortion: Int -> thirdPortionOfWater = newValueOfPortion}
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}