package com.example.h4j4.homeScreenBottomSheet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreenBottomSheet.repository.HomeScreenBottomSheetRepository
import com.example.h4j4.homeScreenBottomSheet.viewState.HomeScreenBottomSheetViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor() : ViewModel() {

    private val repository = HomeScreenBottomSheetRepository()

    private var _State = MutableStateFlow<HomeScreenBottomSheetViewState>(HomeScreenBottomSheetViewState.Inactive)
    val state get() = _State
    fun fetchLogs(waterOrCreatine: WaterOrCreatine, dayOfWeek: DayOfWeek) {

        viewModelScope.launch {

            _State.emit(HomeScreenBottomSheetViewState.Loading)

            when (waterOrCreatine) {

                WaterOrCreatine.WATER -> {

                    val fetchedLogs = repository.fetchAllLogs(dayOfWeek = dayOfWeek, waterOrCreatine = waterOrCreatine)
                    val weeklyIntakeOfWater = repository.fetchWeeklyIntakeOfSubstance(WaterOrCreatine.WATER)

                    _State.emit(HomeScreenBottomSheetViewState.LoadedSuccessfully(fetchedLogs = fetchedLogs, weeklyIntakeOfWater = weeklyIntakeOfWater))
                }

                WaterOrCreatine.CREATINE -> {

                    val fetchedLogs = repository.fetchAllLogs(dayOfWeek = dayOfWeek, waterOrCreatine = waterOrCreatine)
                    val weeklyIntake = repository.fetchWeeklyIntakeOfSubstance(WaterOrCreatine.CREATINE)

                    _State.emit(HomeScreenBottomSheetViewState.LoadedSuccessfully(fetchedLogs = fetchedLogs, weeklyIntakeOfWater = weeklyIntake))
                }
            }
        }
    }

    fun leaveBottomSheetViewModel() {
        viewModelScope.launch {
            _State.emit(HomeScreenBottomSheetViewState.Inactive)
        }
    }

    fun deleteTheLogAndDecreaseAmountOfIngestedSubstance(dayOfWeek: DayOfWeek, nameOfTheLog: String, currentAmountOfIngestedSubstance: Int, amountOfSubstanceToDecrease: Int, waterOrCreatine: WaterOrCreatine) {

        val amountOfSubstanceToUpdate = currentAmountOfIngestedSubstance - amountOfSubstanceToDecrease

        CoroutineScope(Dispatchers.IO).launch {

            repository.deleteTheLog(dayOfWeek = dayOfWeek, nameOfTheLog = nameOfTheLog, waterOrCreatine = waterOrCreatine)
            repository.decreaseAmountOfIngestedSubstance(dayOfWeek = dayOfWeek, amountToUpdate = amountOfSubstanceToUpdate.toString(), waterOrCreatine = waterOrCreatine)
        }
    }
}