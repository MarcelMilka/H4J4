package com.example.h4j4.homeScreen.viewState

sealed class HomeScreenViewState {

    object Loading: HomeScreenViewState()

    data class LoadedSuccessfully(
        val streak: Streak,
        val whatIsTracked: WhatIsTracked,
        val weeklyIntakeOfWater: WeeklyIntakeOfWater,
        val weeklyIntakeOfCreatine: WeeklyIntakeOfCreatine,
        val portionsOfWater: PortionsOfWater,
        val portionsOfCreatine: PortionsOfCreatine
    ): HomeScreenViewState()

    object LoadedUnsuccessfully: HomeScreenViewState()
}