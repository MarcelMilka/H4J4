package com.example.h4j4.homeScreenBottomSheet.repository

import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import java.time.DayOfWeek

interface HomeScreenBottomSheetInterface {
    suspend fun fetchAllLogs(dayOfWeek: DayOfWeek, waterOrCreatine: WaterOrCreatine): List<WaterOrCreatineLog>
    suspend fun fetchWeeklyIntakeOfSubstance(waterOrCreatine: WaterOrCreatine): WeeklyIntakeOfWater
    suspend fun deleteTheLog(dayOfWeek: DayOfWeek, nameOfTheLog: String, waterOrCreatine: WaterOrCreatine)
    suspend fun decreaseAmountOfIngestedSubstance(waterOrCreatine: WaterOrCreatine, dayOfWeek: DayOfWeek, amountToUpdate: String)
}