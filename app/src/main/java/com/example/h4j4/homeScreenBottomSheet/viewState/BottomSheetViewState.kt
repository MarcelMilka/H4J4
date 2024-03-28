package com.example.h4j4.homeScreenBottomSheet.viewState

sealed class BottomSheetViewState {

    object Inactive: BottomSheetViewState()

    object Loading: BottomSheetViewState()

    data class LoadedSuccessfully(val fetchedLogs: List<WaterOrCreatineLog>): BottomSheetViewState()

    object LoadedUnsuccessfully: BottomSheetViewState()
}