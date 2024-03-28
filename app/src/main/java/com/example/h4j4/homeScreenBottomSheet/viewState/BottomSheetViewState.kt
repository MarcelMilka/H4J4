package com.example.h4j4.homeScreenBottomSheet.viewState

import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater

sealed class BottomSheetViewState {

    object Inactive: BottomSheetViewState()

    object Loading: BottomSheetViewState()

    data class LoadedSuccessfully(val fetchedLogs: List<WaterOrCreatineLog>, val weeklyIntakeOfWater: WeeklyIntakeOfWater): BottomSheetViewState()

    object LoadedUnsuccessfully: BottomSheetViewState()
}