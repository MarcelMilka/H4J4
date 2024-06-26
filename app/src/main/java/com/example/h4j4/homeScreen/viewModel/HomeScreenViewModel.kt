package com.example.h4j4.homeScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreen.repository.HomeScreenRepository
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
class HomeScreenViewModel @Inject constructor(private val repository: HomeScreenRepository) : ViewModel() {

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

            repository.checkIfNewWeek()

            val whatIsTracked = repository.checkWhatIsTracked()
            val myUnit = repository.loadData(whatIsTracked)

            val loadedSuccessfully = HomeScreenViewState.LoadedSuccessfully(
                streak = myUnit.streak,
                whatIsTracked = whatIsTracked,
                weeklyIntakeOfWater = myUnit.weeklyIntakeOfWater,
                weeklyIntakeOfCreatine = myUnit.weeklyIntakeOfCreatine,
                portionsOfWater = myUnit.portionsOfWater,
                portionsOfCreatine = myUnit.portionsOfCreatine
            )

            _CurrentState.postValue(loadedSuccessfully)

            _loadChanges(loadedSuccessfully)
        }
    }

    private fun _loadChanges(homeScreenViewState: HomeScreenViewState.LoadedSuccessfully) {

        viewModelScope.launch {

            repository.loadChanges(homeScreenViewState).collect {loadChangesUnit ->

                val updatedValue = HomeScreenViewState.LoadedSuccessfully(
                    streak = homeScreenViewState.streak,
                    whatIsTracked = homeScreenViewState.whatIsTracked,
                    weeklyIntakeOfWater = loadChangesUnit.weeklyIntakeOfWater ?: homeScreenViewState.weeklyIntakeOfWater,
                    weeklyIntakeOfCreatine = loadChangesUnit.weeklyIntakeOfCreatine ?: homeScreenViewState.weeklyIntakeOfCreatine,
                    portionsOfWater = homeScreenViewState.portionsOfWater,
                    portionsOfCreatine = homeScreenViewState.portionsOfCreatine
                )

                _CurrentState.postValue(updatedValue)

            }
        }
    }

    fun refreshData() {

        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(2000)
            loadData()
            _isRefreshing.emit(false)
        }
    }

    suspend fun increaseAmountOfDrankWaterAndAddLog(updatedAmountOfIngestedSubstanceToday: String, sizeOfThePortion: String, waterOrCreatine: WaterOrCreatine) {

        repository.increaseAmountOfIngestedSubstanceToday(finalAmountToUpdate = updatedAmountOfIngestedSubstanceToday, waterOrCreatine = waterOrCreatine)
        repository.addAnotherPortionOfSubstanceToLogs(sizeOfThePortion = sizeOfThePortion, waterOrCreatine = waterOrCreatine)
    }
}