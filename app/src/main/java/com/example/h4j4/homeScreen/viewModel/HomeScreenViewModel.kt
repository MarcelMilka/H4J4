package com.example.h4j4.homeScreen.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

object HomeScreenViewModel: ViewModel() {

    private var _CurrentState = MutableLiveData<HomeScreenViewState>()
    val currentState: MutableLiveData<HomeScreenViewState>
        get() {
            return _CurrentState
        }

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    init {

        loadData()
    }

    private fun loadData() {

        viewModelScope.launch {

            _CurrentState.postValue(HomeScreenViewState.Loading)
            delay(5000)
            _CurrentState.postValue(HomeScreenViewState.LoadedSuccessfully("Hey there!"))
        }
    }

    public fun refreshData() {

        viewModelScope.launch {
            _isRefreshing.emit(true)
            Log.d("test of swipe-to-refresh", "is refreshing")
            delay(5000)
            _isRefreshing.emit(false)
            Log.d("test of swipe-to-refresh", "refreshed")
        }
    }
}