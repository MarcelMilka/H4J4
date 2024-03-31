package com.example.h4j4.user.viewState

import com.example.h4j4.homeScreen.viewState.PortionsOfCreatine
import com.example.h4j4.homeScreen.viewState.PortionsOfWater
import com.example.h4j4.homeScreen.viewState.WhatIsTracked

sealed class UserViewState {

    object Loading: UserViewState()

    data class LoadedSuccessfully(
        val whatIsTracked: WhatIsTracked,
        val dailyAmountOfWaterToIngest: DailyAmountOfWaterToIngest,
        val portionsOfWater: PortionsOfWater,
        val dailyAmountOfCreatineToIngest: DailyAmountOfCreatineToIngest,
        val portionsOfCreatine: PortionsOfCreatine
    ): UserViewState()
}
