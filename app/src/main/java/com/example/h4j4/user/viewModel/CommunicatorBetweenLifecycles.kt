package com.example.h4j4.user.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.h4j4.user.viewState.CommunicateBetweenLifecycles
import kotlinx.coroutines.flow.MutableStateFlow

object CommunicatorBetweenLifecycles: ViewModel() {

    private var _state = MutableLiveData(CommunicateBetweenLifecycles(false, false))
    val state: MutableLiveData<CommunicateBetweenLifecycles>
        get() {return _state}

    fun enableSaving() {
        _state.postValue(CommunicateBetweenLifecycles(savingIsEnabled = true, displayDialog = false))
    }

    fun disableSaving() {
        _state.postValue(CommunicateBetweenLifecycles(savingIsEnabled = false, displayDialog = false))
    }

    fun displayDialog() {
        _state.postValue(CommunicateBetweenLifecycles(savingIsEnabled = true, displayDialog = true))
    }

    fun hideDialog() {
        _state.postValue(CommunicateBetweenLifecycles(savingIsEnabled = true, displayDialog = false))
    }

}