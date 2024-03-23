package com.example.h4j4.homeScreenBottomSheet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreenBottomSheet.repository.BottomSheetRepository
import com.example.h4j4.homeScreenBottomSheet.viewState.BottomSheetViewState
import com.example.h4j4.homeScreenBottomSheet.viewState.FetchedLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor() : ViewModel() {

    private val repository = BottomSheetRepository()

    private var _State = MutableStateFlow<BottomSheetViewState>(BottomSheetViewState.Inactive)
    val state get() = _State
//    fun loadData() {
//
//        viewModelScope.launch {
//
//            _State.emit(BottomSheetViewState.Loading)
//            delay(5000)
//            val fetchedLogs = repository.fetchAllLogsOfWaterIntake()
//            _State.emit(BottomSheetViewState.LoadedSuccessfully(fetchedLogs = fetchedLogs))
//        }
//    }

    fun stop() {
        viewModelScope.launch {
            _State.emit(BottomSheetViewState.Inactive)
        }
    }
}