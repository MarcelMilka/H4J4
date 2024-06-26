package com.example.h4j4.user.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.user.dataClasses.UserSettingsChange
import com.example.h4j4.user.repository.UserRepository
import com.example.h4j4.user.viewState.UserViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel: ViewModel() {

    val repository = UserRepository()

    private var _uiState = MutableLiveData<UserViewState>()
    public val uiState: MutableLiveData<UserViewState>
        get() {return _uiState}

    init {
        fetchData()
    }

    private fun fetchData() {

        viewModelScope.launch {

            _uiState.postValue(UserViewState.Loading)

            val userUnit = repository.fetchData()

            _uiState.postValue(UserViewState.LoadedSuccessfully(
                whatIsTracked = userUnit.whatIsTracked,
                dailyAmountOfWaterToIngest = userUnit.dailyAmountOfWaterToIngest,
                portionsOfWater = userUnit.portionsOfWater,
                dailyAmountOfCreatineToIngest = userUnit.dailyAmountOfCreatineToIngest,
                portionsOfCreatine = userUnit.portionsOfCreatine
            ))
        }
    }

    fun updateValues(changes: MutableList<UserSettingsChange>) {

        CoroutineScope(Dispatchers.IO).launch {
            repository.updateValues(changes = changes)
        }
    }
}