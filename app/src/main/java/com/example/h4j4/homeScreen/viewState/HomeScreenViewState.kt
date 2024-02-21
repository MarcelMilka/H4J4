package com.example.h4j4.homeScreen.viewState

sealed class HomeScreenViewState {

    object Loading: HomeScreenViewState()

    data class LoadedSuccessfully(var x: String): HomeScreenViewState()

    object LoadedUnsuccessfully: HomeScreenViewState()

}