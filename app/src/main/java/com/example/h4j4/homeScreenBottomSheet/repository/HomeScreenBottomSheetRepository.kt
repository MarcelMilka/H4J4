package com.example.h4j4.homeScreenBottomSheet.repository

import android.util.Log
import com.example.h4j4.homeScreen.viewState.WeeklyIntakeOfWater
import com.example.h4j4.homeScreenBottomSheet.viewState.WaterOrCreatineLog
import com.google.firebase.firestore.FirebaseFirestore
import java.time.DayOfWeek
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeScreenBottomSheetRepository: HomeScreenBottomSheetInterface {

    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    override suspend fun fetchAllWaterLogs(dayOfWeek: DayOfWeek): List<WaterOrCreatineLog> {

        val fetchedLogs = mutableListOf<WaterOrCreatineLog>()

        return suspendCoroutine { continuation ->

            try {
                firebase.collection("me").document("Logs of water intake")
                    .collection("$dayOfWeek")
                    .get()
                    .addOnSuccessListener {containerOfLogs ->

                        val allLogs = containerOfLogs.documents

                        try {
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
}