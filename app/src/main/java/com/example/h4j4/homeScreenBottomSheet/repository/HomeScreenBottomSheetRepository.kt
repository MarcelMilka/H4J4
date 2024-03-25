package com.example.h4j4.homeScreenBottomSheet.repository

import android.util.Log
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import com.google.firebase.firestore.FirebaseFirestore
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeScreenBottomSheetRepository: HomeScreenBottomSheetInterface {

    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    override suspend fun fetchAllWaterLogs(dayOfWeek: DayOfWeek): List<WaterOrCreatineLog> {

        val fetchedLogs = mutableListOf<WaterOrCreatineLog>()

        return suspendCoroutine { continuation ->

            firebase.collection("me").document("Logs of water intake")
                .collection("$dayOfWeek")
                .get()
                .addOnSuccessListener {containerOfLogs ->

                    val allLogs = containerOfLogs.documents

                    allLogs.forEach { log ->

                        log.toObject(WaterOrCreatineLog::class.java)?.let {
                            Log.d("fetchAllWaterLogs:", "$it")


                            fetchedLogs.add(WaterOrCreatineLog(
                                amount = it.amount,
                                time = it.time,
                                nameOfTheLog = log.id
                            ))
                        }

                    }
                    continuation.resume(fetchedLogs)
                }
        }
    }

    override suspend fun deleteWaterLog(dayOfWeek: DayOfWeek, nameOfTheLog: String) {

        firebase.collection("me").document("Logs of water intake")
            .collection("$dayOfWeek").document(nameOfTheLog)
            .delete()
            .addOnSuccessListener {Log.d("deleting the log", "The log has been deleted successfully.")}
            .addOnFailureListener {Log.d("deleting the log", "An error occurred while deleting the log.")}
    }

    override suspend fun decreaseAmountOfIngestedWater(dayOfWeek: DayOfWeek, decreasedAmountOfWaterToUpdate: String) {

        var toUpdate = mutableMapOf<String, Any>()
        toUpdate.put(dayOfWeek.toString().lowercase(), decreasedAmountOfWaterToUpdate)

        firebase.collection("me").document("Weekly intake of creatine")
            .update(toUpdate)
    }
}