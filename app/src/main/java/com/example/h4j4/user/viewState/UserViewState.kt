package com.example.h4j4.user.viewState

import com.example.h4j4.homeScreen.viewState.*

sealed class UserViewState {

    object Loading: UserViewState()

    data class LoadedSuccessfully(
        val whatIsTracked: WhatIsTracked,
        val dailyIngestionOfWater: DailyIngestionOfWater,
        val dailyIngestionOfCreatine: DailyIngestionOfCreatine,
        val portionsOfWater: PortionsOfWater,
        val portionsOfCreatine: PortionsOfCreatine
    ): UserViewState()

    object FailedToLoadData: UserViewState()

}