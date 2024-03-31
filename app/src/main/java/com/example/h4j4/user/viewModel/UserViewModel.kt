package com.example.h4j4.user.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreen.viewState.WhatIsTracked
import com.example.h4j4.user.repository.UserRepository
import com.example.h4j4.user.viewState.UserUnit
import com.example.h4j4.user.viewState.UserViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel: ViewModel() {

    val repository = UserRepository()

    private var _uiState = MutableLiveData<UserViewState>()

    val uiState: MutableLiveData<UserViewState>
        get() {return _uiState}

    init {
        fetchData()
    }

    private fun fetchData () {

        viewModelScope.launch {

            _uiState.postValue(UserViewState.Loading)

            val whatIsTracked: WhatIsTracked = repository.checkWhatIsTracked()
            val fetchedData: UserUnit = repository.fetchData(whatIsTracked)

            _uiState.postValue(
                UserViewState.LoadedSuccessfully(
                    whatIsTracked = whatIsTracked,
                    dailyIngestionOfCreatine = fetchedData.dailyIngestionOfCreatine,
                    dailyIngestionOfWater = fetchedData.dailyIngestionOfWater,
                    portionsOfWater = fetchedData.portionsOfWater,
                    portionsOfCreatine = fetchedData.portionsOfCreatine
                )
            )
        }
    }
}