package com.example.h4j4.homeScreen.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.h4j4.homeScreen.ui.ControllPanel
import com.example.h4j4.homeScreen.ui.DiagramOfWeeklyCreatineIntake
import com.example.h4j4.homeScreen.ui.DiagramOfWeeklyWaterIntake
import com.example.h4j4.homeScreen.ui.Navbar
import com.example.h4j4.homeScreen.viewModel.HomeScreenViewModel
import com.example.h4j4.homeScreenBottomSheet.view.modalBottomSheet
import com.example.h4j4.homeScreenBottomSheet.viewModel.BottomSheetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.time.DayOfWeek
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : ComponentActivity() {

    @Inject
    lateinit var viewModel: HomeScreenViewModel

    var bottomSheetViewModel = BottomSheetViewModel
    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        a reference to the splash screen
        installSplashScreen().apply {

        }

        viewModel.currentState.observe(this@HomeScreen) { homeScreenViewState ->

            setContent {

//                Pull to refresh
                val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
                val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {viewModel.refreshData()})

//                Modal bottom sheet
                val sheetState = rememberModalBottomSheetState()
                var showBottomSheet = MutableSharedFlow<Boolean>(false) // var showBottomSheet by remember { mutableStateOf(false) }
                var dayBottomSheet by remember { mutableStateOf(DayOfWeek.MONDAY) }

                LaunchedEffect(showBottomSheet) {
                    Log.d("HomeScreen - showBottomSheet", "show bottom sheet = $showBottomSheet")
                }

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                        .pullRefresh(pullRefreshState)
                        .verticalScroll(rememberScrollState()),

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    content = {

                        Navbar(homeScreenViewState)

                        Spacer(modifier = Modifier.height(20.dp))

                        ControllPanel(homeScreenViewState)

                        Spacer(modifier = Modifier.height(49.dp))

                        DiagramOfWeeklyCreatineIntake(homeScreenViewState)

                        Spacer(modifier = Modifier.height(20.dp))

                        DiagramOfWeeklyWaterIntake(homeScreenViewState, showBottomSheet) {stateOfBottomSheet ->
                            showBottomSheet = stateOfBottomSheet.displayBottomSheet
                            dayBottomSheet = stateOfBottomSheet.dayToDisplay
                        }

                        if (showBottomSheet == true) {
                            modalBottomSheet(sheetState, BottomSheetViewModel, dayBottomSheet) {visibilityOfBottomSheet -> showBottomSheet = visibilityOfBottomSheet}
                        }
                    }
                )

                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState
                    // TODO: Center the pull to refresh
                )
            }
        }
    }
}
