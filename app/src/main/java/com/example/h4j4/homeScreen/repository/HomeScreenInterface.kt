package com.example.h4j4.homeScreen.repository

import android.util.Log
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.homeScreen.viewState.LoadChangesUnit
import com.example.h4j4.homeScreen.viewState.MyUnit
import com.example.h4j4.homeScreen.viewState.WhatIsTracked
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.Flow
import javax.inject.Inject

interface HomeScreenInterface {

    suspend fun checkWhatIsTracked(): WhatIsTracked
    suspend fun checkIfNewWeek()
    suspend fun loadData(whatIsTracked: WhatIsTracked?): MyUnit
    suspend fun loadChanges(homeScreenViewState: HomeScreenViewState.LoadedSuccessfully) : kotlinx.coroutines.flow.Flow<LoadChangesUnit>

    suspend fun addAnotherPortionOfWaterToLogs(portion: String)
    suspend fun increaseAmountOfDrankWaterToday(finalAmountToUpdate: String)
}