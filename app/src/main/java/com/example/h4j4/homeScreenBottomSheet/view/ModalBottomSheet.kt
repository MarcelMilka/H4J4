package com.example.h4j4.homeScreenBottomSheet.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h4j4.BottomSheetLauncher
import com.example.h4j4.homeScreenBottomSheet.viewModel.BottomSheetViewModel
import com.example.h4j4.homeScreenBottomSheet.viewState.BottomSheetViewState
import com.example.h4j4.ui.theme.Sixty
import java.time.DayOfWeek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun modalBottomSheet(
    sheetState: SheetState,
    bottomSheetViewModel: BottomSheetViewModel,
    bottomSheetLauncher: BottomSheetLauncher,
    bottomSheetWithdrawal: (BottomSheetLauncher) -> Unit
) {

    if (bottomSheetLauncher.launch) {
//
//        bottomSheetViewModel.loadData()

        ModalBottomSheet(

            modifier = Modifier
                .fillMaxHeight(0.5f),

            containerColor = Sixty,
            onDismissRequest = {
                bottomSheetWithdrawal(BottomSheetLauncher(DayOfWeek.MONDAY, false))
                bottomSheetViewModel.stop()
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

                        Divider()

                        when (uiState) {
                            BottomSheetViewState.Loading -> {
                                Text(text = "Loading", color = Color.White)
                            }

                            is BottomSheetViewState.LoadedSuccessfully -> {

                                Text("${uiState.fetchedLogs}")
                            }
                            BottomSheetViewState.LoadedUnsuccessfully -> {
                                Text(text = "Failed to load data", color = Color.White)
                            }
                            BottomSheetViewState.Inactive -> {

                            }
                        }
                    }
                )
            }
        )
    }
}