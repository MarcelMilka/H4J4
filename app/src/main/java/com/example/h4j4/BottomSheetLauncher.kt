package com.example.h4j4

import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import java.time.DayOfWeek

data class BottomSheetLauncher(
    var dayOfWeek: DayOfWeek,
    var launch: Boolean,
    var waterOrCreatine: WaterOrCreatine
)
