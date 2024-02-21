package com.example.h4j4.homeScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object HomeScreenViewModel: ViewModel() {

    private var privateCurrentState = MutableLiveData<HomeScreenViewState>()
    val publicCurrentState: MutableLiveData<HomeScreenViewState>
        get() {
            return privateCurrentState
        }

    init {

        loadData()
    }

    private fun loadData() {

        viewModelScope.launch {

            privateCurrentState.postValue(HomeScreenViewState.Loading)
            delay(5000)
            privateCurrentState.postValue(HomeScreenViewState.LoadedSuccessfully("Hey there!"))
        }
    }
}