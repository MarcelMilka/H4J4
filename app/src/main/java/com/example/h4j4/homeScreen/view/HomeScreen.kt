package com.example.h4j4.homeScreen.view

import android.os.Bundle
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
import androidx.compose.material3.ModalBottomSheet
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : ComponentActivity() {

    @Inject
    lateinit var viewModel: HomeScreenViewModel
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
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }


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

                        DiagramOfWeeklyWaterIntake(homeScreenViewState)

                        if (showBottomSheet) {
                            ModalBottomSheet(

                                onDismissRequest = {!showBottomSheet},
                                sheetState = sheetState,
                                content = {}
                            )
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
