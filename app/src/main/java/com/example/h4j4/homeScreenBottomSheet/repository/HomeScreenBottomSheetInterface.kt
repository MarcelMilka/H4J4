package com.example.h4j4.homeScreenBottomSheet.repository

import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import java.time.DayOfWeek

interface HomeScreenBottomSheetInterface {

    suspend fun fetchAllWaterLogs(dayOfWeek: DayOfWeek): List<WaterOrCreatineLog>
}