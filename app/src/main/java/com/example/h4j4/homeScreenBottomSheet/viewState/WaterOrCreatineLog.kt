package com.example.h4j4.homeScreenBottomSheet.viewState

data class WaterOrCreatineLog(
    val amount: String,
    val time: String,
    val nameOfTheLog: String,
) {
    constructor() : this ("error", "error", "error")
}
