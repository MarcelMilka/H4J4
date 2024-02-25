package com.example.h4j4.homeScreen

import android.util.Log
import com.example.h4j4.homeScreen.viewState.MyUnit
import com.example.h4j4.homeScreen.viewState.WhatIsTracked

interface HomeScreenInterface {

    suspend fun checkWhatIsTracked(): WhatIsTracked

    suspend fun checkIfNewWeek()

    suspend fun loadData(whatIsTracked: WhatIsTracked?): MyUnit
}