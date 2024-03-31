package com.example.h4j4.user.viewState

import com.example.h4j4.homeScreen.viewState.PortionsOfCreatine
import com.example.h4j4.homeScreen.viewState.PortionsOfWater
import com.example.h4j4.homeScreen.viewState.WhatIsTracked

data class UserUnit(
    val whatIsTracked: WhatIsTracked,
    val dailyAmountOfWaterToIngest: DailyAmountOfWaterToIngest,
    val portionsOfWater: PortionsOfWater,
    val dailyAmountOfCreatineToIngest: DailyAmountOfCreatineToIngest,
    val portionsOfCreatine: PortionsOfCreatine
)
