package com.example.h4j4.homeScreenBottomSheet.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreenBottomSheet.repository.HomeScreenBottomSheetRepository
import com.example.h4j4.homeScreenBottomSheet.viewState.BottomSheetViewState
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor() : ViewModel() {

    private val repository = HomeScreenBottomSheetRepository()

    private var _State = MutableStateFlow<BottomSheetViewState>(BottomSheetViewState.Inactive)
    val state get() = _State
    fun fetchLogs(waterOrCreatine: WaterOrCreatine, dayOfWeek: DayOfWeek) {

        viewModelScope.launch {

            _State.emit(BottomSheetViewState.Loading)

            when (waterOrCreatine) {

                WaterOrCreatine.WATER -> {

                    val fetchedLogs = repository.fetchAllWaterLogs(dayOfWeek = dayOfWeek)

                    _State.emit(BottomSheetViewState.LoadedSuccessfully(fetchedLogs = fetchedLogs))
                }

                WaterOrCreatine.CREATINE -> {

                }
            }
        }
    }

    fun deleteLog (waterOrCreatineLog: WaterOrCreatineLog, nameOfTheLog: String) {

        viewModelScope.launch {
        }
    }

    fun leaveBottomSheetViewModel() {
        viewModelScope.launch {
            _State.emit(BottomSheetViewState.Inactive)
        }
    }
}