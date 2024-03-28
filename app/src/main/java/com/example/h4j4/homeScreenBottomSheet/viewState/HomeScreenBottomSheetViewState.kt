package com.example.h4j4.homeScreenBottomSheet.viewState

import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater

sealed class HomeScreenBottomSheetViewState {

    object Inactive: HomeScreenBottomSheetViewState()

    object Loading: HomeScreenBottomSheetViewState()

    data class LoadedSuccessfully(val fetchedLogs: List<WaterOrCreatineLog>, val weeklyIntakeOfWater: WeeklyIntakeOfWater): HomeScreenBottomSheetViewState()

    object LoadedUnsuccessfully: HomeScreenBottomSheetViewState()
}