package com.example.h4j4.homeScreenBottomSheet.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h4j4.homeScreenBottomSheet.viewState.BottomSheetViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object BottomSheetViewModel: ViewModel() {

    private var _State = MutableStateFlow<BottomSheetViewState>(BottomSheetViewState.Inactive)
    val state get() = _State

    init {

        test()
    }

    fun test() {

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