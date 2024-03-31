package com.example.h4j4.homeScreen.repository

import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.homeScreen.viewState.LoadChangesUnit
import com.example.h4j4.homeScreen.viewState.HomeScreenUnit
import com.example.h4j4.homeScreen.viewState.WhatIsTracked

interface HomeScreenInterface {

    suspend fun checkWhatIsTracked(): WhatIsTracked
    suspend fun checkIfNewWeek()
    suspend fun loadData(whatIsTracked: WhatIsTracked?): HomeScreenUnit
    suspend fun loadChanges(homeScreenViewState: HomeScreenViewState.LoadedSuccessfully) : kotlinx.coroutines.flow.Flow<LoadChangesUnit>

    suspend fun addAnotherPortionOfWaterToLogs(portion: String)
    suspend fun increaseAmountOfDrankWaterToday(finalAmountToUpdate: String)
}