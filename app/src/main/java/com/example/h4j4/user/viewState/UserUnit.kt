package com.example.h4j4.user.viewState

import com.example.h4j4.homeScreen.viewState.*

data class UserUnit(
    val whatIsTracked: WhatIsTracked,
    val dailyIngestionOfWater: DailyIngestionOfWater,
    val dailyIngestionOfCreatine: DailyIngestionOfCreatine,
    val portionsOfWater: PortionsOfWater,
    val portionsOfCreatine: PortionsOfCreatine
)
