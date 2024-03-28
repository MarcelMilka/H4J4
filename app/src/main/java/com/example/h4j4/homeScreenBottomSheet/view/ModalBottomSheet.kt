package com.example.h4j4.homeScreenBottomSheet.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h4j4.BottomSheetLauncher
import com.example.h4j4.homeScreenBottomSheet.viewModel.BottomSheetViewModel
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.homeScreenBottomSheet.viewState.HomeScreenBottomSheetViewState
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import com.example.h4j4.ui.theme.Sixty
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun modalBottomSheet(
    sheetState: SheetState,
    bottomSheetViewModel: BottomSheetViewModel,
    bottomSheetLauncher: BottomSheetLauncher,
    bottomSheetWithdrawal: (BottomSheetLauncher) -> Unit,
    deleteTheLog: (dayOfWeek: DayOfWeek, nameOfTheLog: String, currentAmountOfDrankWater: Int, amountOfWaterToDecrease: Int) -> Unit
) {

    if (bottomSheetLauncher.launch) {

        bottomSheetViewModel.fetchLogs(waterOrCreatine = bottomSheetLauncher.waterOrCreatine, dayOfWeek = bottomSheetLauncher.dayOfWeek)

        ModalBottomSheet(

            modifier = Modifier
                .fillMaxHeight(0.5f),

            containerColor = Sixty,
            onDismissRequest = {
                bottomSheetWithdrawal(BottomSheetLauncher(DayOfWeek.MONDAY, false, WaterOrCreatine.WATER))
                bottomSheetViewModel.leaveBottomSheetViewModel()
            },
            sheetState = sheetState,
            content = {

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,

                    content = {

                        val uiState = bottomSheetViewModel.state.collectAsState().value

//                    Day, date
                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),

                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,

                            content =  {

                                Text(text = bottomSheetLauncher.dayOfWeek.toString().lowercase().capitalize(), color = Color.White, fontSize = 25.sp)
                            }
                        )

                        Divider(modifier = Modifier.height(2.dp))

                        when (uiState) {
                            HomeScreenBottomSheetViewState.Loading -> {
                                Text(text = "Loading", color = Color.White)
                            }

                            is HomeScreenBottomSheetViewState.LoadedSuccessfully -> {

                                if (uiState.fetchedLogs.isEmpty()) {

                                    Text("Empty")
                                }

                                else {

                                    LazyColumn (

                                        modifier = Modifier
                                            .fillMaxSize(),

                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally,

                                        content = {

                                            val currentAmountOfDrankWater = when (bottomSheetLauncher.dayOfWeek) {

                                                DayOfWeek.MONDAY -> {uiState.weeklyIntakeOfWater.monday}
                                                DayOfWeek.TUESDAY -> {uiState.weeklyIntakeOfWater.tuesday}
                                                DayOfWeek.WEDNESDAY -> {uiState.weeklyIntakeOfWater.wednesday}
                                                DayOfWeek.THURSDAY -> {uiState.weeklyIntakeOfWater.thursday}
                                                DayOfWeek.FRIDAY -> {uiState.weeklyIntakeOfWater.friday}
                                                DayOfWeek.SATURDAY -> {uiState.weeklyIntakeOfWater.saturday}
                                                DayOfWeek.SUNDAY -> {uiState.weeklyIntakeOfWater.sunday}
                                            }

                                            items(uiState.fetchedLogs.dropLast(1)) { waterOrCreatineLog ->

                                                log(bottomSheetLauncher.dayOfWeek, waterOrCreatineLog, waterOrCreatine = bottomSheetLauncher.waterOrCreatine) {
                                                    deleteTheLog(
                                                        bottomSheetLauncher.dayOfWeek,
                                                        waterOrCreatineLog.nameOfTheLog,
                                                        currentAmountOfDrankWater,
                                                        waterOrCreatineLog.amount.toInt()
                                                    )
                                                }
                                                Divider(modifier = Modifier.height(0.25.dp))
                                            }

                                            item {
                                                log(bottomSheetLauncher.dayOfWeek, uiState.fetchedLogs.last(), waterOrCreatine = bottomSheetLauncher.waterOrCreatine) {
                                                    deleteTheLog(
                                                        bottomSheetLauncher.dayOfWeek,
                                                        uiState.fetchedLogs.last().nameOfTheLog,
                                                        currentAmountOfDrankWater,
                                                        uiState.fetchedLogs.last().amount.toInt()
                                                    )
                                                }
                                            }
                                        }
                                    )
                                }
                            }

                            HomeScreenBottomSheetViewState.LoadedUnsuccessfully -> {
                                Text(text = "Failed to load data", color = Color.White)
                            }

                            HomeScreenBottomSheetViewState.Inactive -> {

                            }
                        }
                    }
                )
            }
        )
    }
}

@Composable fun log (representedDayOfWeek: DayOfWeek ,informationAboutTheLog: WaterOrCreatineLog, waterOrCreatine: WaterOrCreatine, deleteTheLog: () -> Unit) {

    val enabledToDelete = if (LocalDate.now().dayOfWeek == representedDayOfWeek) {
        true
    }

    else {
        false
    }

    val suffix = when (waterOrCreatine) {
        WaterOrCreatine.WATER -> {
            "ml"
        }
        WaterOrCreatine.CREATINE -> {
            "ml"
        }
    }

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,

        content = {

            Column(

                modifier = Modifier
                    .fillMaxWidth(0.5f),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,

                content = {

                    Text(text = "${informationAboutTheLog.amount} $suffix", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.W500)
                    Text(text = informationAboutTheLog.time, fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.W300)
                }
            )

            Column(

                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End,

                content = {

                    if (enabledToDelete) {
                        IconButton(

                            onClick = {deleteTheLog()},

                            content = {
                                Icon(

                                    imageVector = Icons.Rounded.Delete,
                                    tint = Color.White,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            )
        }
    )
}