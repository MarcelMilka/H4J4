package com.example.h4j4.homeScreenBottomSheet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreenBottomSheet.viewState.BottomSheetViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor() : ViewModel() {

    private var _State = MutableStateFlow<BottomSheetViewState>(BottomSheetViewState.Inactive)
    val state get() = _State
    fun loadData() {

        viewModelScope.launch {

            _State.emit(BottomSheetViewState.Loading)
            delay(5000)
            _State.emit(BottomSheetViewState.LoadedSuccessfully(logs = "Okay!"))
        }
    }

    fun stop() {
        viewModelScope.launch {
            _State.emit(BottomSheetViewState.Inactive)
        }
    }
}