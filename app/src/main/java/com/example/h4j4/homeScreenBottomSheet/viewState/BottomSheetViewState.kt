package com.example.h4j4.homeScreenBottomSheet.viewState

sealed class BottomSheetViewState {

    object Inactive: BottomSheetViewState()

    object Loading: BottomSheetViewState()

    data class LoadedSuccessfully(val logs: String): BottomSheetViewState()

    object LoadedUnsuccessfully: BottomSheetViewState()
}