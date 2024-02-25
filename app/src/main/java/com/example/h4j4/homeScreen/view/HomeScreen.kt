package com.example.h4j4.homeScreen.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.h4j4.homeScreen.viewModel.HomeScreenViewModel
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState


class HomeScreen : ComponentActivity() {

    val viewModel = HomeScreenViewModel
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        a reference to the splash screen
        installSplashScreen().apply {}

        viewModel.currentState.observe(this@HomeScreen) {

            when (it) {
                HomeScreenViewState.Loading -> {
                    Log.d("test of vm", "loading")
                }

                is HomeScreenViewState.LoadedSuccessfully -> {
                    Log.d("test of vm", "loaded successfully")
                    Log.d("test of vm", "WhatIsTracked = ${it.whatIsTracked}")
                    Log.d("test of vm", "WhatIsTracked = ${it.portionsOfCreatine}")
                    Log.d("test of vm", "WhatIsTracked = ${it.streak}")
                    Log.d("test of vm", "WhatIsTracked = ${it.portionsOfWater}")
                    Log.d("test of vm", "WhatIsTracked = ${it.weeklyIntakeOfCreatine}")
                    Log.d("test of vm", "WhatIsTracked = ${it.weeklyIntakeOfWater}")
                }

                HomeScreenViewState.LoadedUnsuccessfully -> {
                    Log.d("test of vm", "loaded unsuccessfully")
                }
            }
        }

        setContent {

            val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
            val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {viewModel.refreshData()})

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
                    .verticalScroll(rememberScrollState()),

                content = {
                    Text("Hey there!")
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
