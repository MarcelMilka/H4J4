package com.example.h4j4.homeScreenBottomSheet.repository

import android.util.Log
import com.example.h4j4.homeScreenBottomSheet.HomeScreenBottomSheetInterface
import com.example.h4j4.homeScreenBottomSheet.viewState.FetchedLog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BottomSheetRepository: HomeScreenBottomSheetInterface {

    private val _firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _logsOfWaterIntake = _firebase.document("me").collection("Logs of water intake")

    override suspend fun fetchAllLogsOfWaterIntake() : MutableList<FetchedLog> {

        val currentDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek

        val logsOfThisDay = _firebase.collection("me").document("Logs of water intake").collection("$currentDayOfWeek")

        var fetchedAllLogs = mutableListOf<FetchedLog>(FetchedLog("500", "10:00", "hwdp"))

        return suspendCoroutine { continuation ->

            logsOfThisDay.get()
                .addOnSuccessListener {getAllLogs ->

                    continuation.resume(fetchedAllLogs)
                }
        }
    }

    override suspend fun deleteTheLog() {
        TODO("Not yet implemented")
    }

}