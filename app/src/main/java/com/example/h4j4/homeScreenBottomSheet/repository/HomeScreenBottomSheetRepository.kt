package com.example.h4j4.homeScreenBottomSheet.repository

import android.util.Log
import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater
import com.example.h4j4.homeScreenBottomSheet.viewModel.WaterOrCreatine
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import com.google.firebase.firestore.FirebaseFirestore
import java.time.DayOfWeek
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeScreenBottomSheetRepository: HomeScreenBottomSheetInterface {

    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    override suspend fun fetchAllLogs(dayOfWeek: DayOfWeek, waterOrCreatine: WaterOrCreatine): List<WaterOrCreatineLog> {

        val documentPath = when (waterOrCreatine) {
            WaterOrCreatine.WATER -> {"Logs of water intake"}
            WaterOrCreatine.CREATINE -> {"Logs of creatine intake"}
        }
        val fetchedLogsToPushFurther = mutableListOf<WaterOrCreatineLog>()

        return suspendCoroutine { continuation ->

            try {
                firebase.collection("me").document(documentPath)
                    .collection("$dayOfWeek")
                    .get()
                    .addOnSuccessListener {containerOfLogs ->

                        val allLogs = containerOfLogs.documents

                        try {
                            allLogs.forEach { log ->

                                log.toObject(WaterOrCreatineLog::class.java)?.let {

                                    fetchedLogsToPushFurther.add(WaterOrCreatineLog(
                                        amount = it.amount,
                                        time = it.time,
                                        nameOfTheLog = log.id
                                    ))
                                }

                            }
                            continuation.resume(fetchedLogsToPushFurther)
                        }

                        catch (e: Exception) {
                            Log.d("the error i have just encountered", "$e")
                        }

                    }
            }

            catch (e: Exception) {
                Log.d("woooh", "$e")
            }
        }
    }

    override suspend fun fetchWeeklyIntakeOfSubstance(waterOrCreatine: WaterOrCreatine): WeeklyIntakeOfWater {

        val documentPath = when (waterOrCreatine) {
            WaterOrCreatine.WATER -> {"Weekly intake of water"}
            WaterOrCreatine.CREATINE -> {"Weekly intake of creatine"}
        }

        return suspendCoroutine { continuation ->

            firebase.collection("me").document(documentPath)
                .get()
                .addOnSuccessListener {

                    val dailyGoal = it.getString("daily goal")
                    val monday = it.getString("monday")
                    val tuesday = it.getString("tuesday")
                    val wednesday = it.getString("wednesday")
                    val thursday = it.getString("thursday")
                    val friday = it.getString("friday")
                    val saturday = it.getString("saturday")
                    val sunday = it.getString("sunday")

                    if (
                        dailyGoal != null &&
                        monday != null &&
                        tuesday != null &&
                        wednesday != null &&
                        thursday != null &&
                        friday != null &&
                        saturday != null &&
                        sunday != null
                    ) {
                        continuation.resume(
                            WeeklyIntakeOfWater(
                                dailyGoal.toInt(),
                                monday.toInt(),
                                tuesday.toInt(),
                                wednesday.toInt(),
                                thursday.toInt(),
                                friday.toInt(),
                                saturday.toInt(),
                                sunday.toInt())
                        )
                    }
                }
        }
    }

    override suspend fun deleteTheLog(dayOfWeek: DayOfWeek, nameOfTheLog: String, waterOrCreatine: WaterOrCreatine) {

        val documentPath = when (waterOrCreatine) {
            WaterOrCreatine.WATER -> {"Logs of water intake"}
            WaterOrCreatine.CREATINE -> {"Logs of creatine intake"}
        }

        firebase.collection("me").document(documentPath)
            .collection("$dayOfWeek")
            .document(nameOfTheLog)
            .delete()
            .addOnSuccessListener { Log.d("uuu la la", "The log ${nameOfTheLog} has been deleted correctly.") }
            .addOnFailureListener { Log.d("uuu la la", "A problem occured while deleting the log $nameOfTheLog") }
    }

    override suspend fun decreaseAmountOfIngestedSubstance(waterOrCreatine: WaterOrCreatine, dayOfWeek: DayOfWeek, amountToUpdate: String) {

        val documentPath = when (waterOrCreatine) {
            WaterOrCreatine.WATER -> {"Weekly intake of water"}
            WaterOrCreatine.CREATINE -> {"Weekly intake of creatine"}
        }

        var fieldToUpdate = mutableMapOf<String, Any>()
        fieldToUpdate.put(dayOfWeek.toString().lowercase(), amountToUpdate)

        firebase.collection("me").document(documentPath)
            .update(fieldToUpdate)
            .addOnSuccessListener { Log.d("uuu la la", "The amount of drank water has been updated successfully.") }
            .addOnFailureListener { Log.d("uuu la la", "A problem occured while updating the amount of drank water.") }
    }
}