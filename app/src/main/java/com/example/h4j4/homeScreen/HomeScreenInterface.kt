package com.example.h4j4.homeScreen

import android.util.Log
import com.example.h4j4.homeScreen.enumClasses.WaterOrCreatine
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.homeScreen.viewState.LoadChangesUnit
import com.example.h4j4.homeScreen.viewState.MyUnit
import com.example.h4j4.homeScreen.viewState.WhatIsTracked
import java.util.concurrent.Flow

interface HomeScreenInterface {

    suspend fun checkWhatIsTracked(): WhatIsTracked

    suspend fun checkIfNewWeek()

    suspend fun loadData(whatIsTracked: WhatIsTracked?): MyUnit
    suspend fun loadChanges(homeScreenViewState: HomeScreenViewState.LoadedSuccessfully) : kotlinx.coroutines.flow.Flow<LoadChangesUnit>

    suspend fun addAnotherPortionOfWaterOrCreatine(waterOrCreatine: WaterOrCreatine, currentAmount: String, amountToInrease: String)

    suspend fun addNewPortionOfWaterOrCreatine()
}