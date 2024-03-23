package com.example.h4j4.homeScreenBottomSheet

import com.example.h4j4.homeScreenBottomSheet.viewState.FetchedLog
import com.google.firebase.firestore.FirebaseFirestore

interface HomeScreenBottomSheetInterface {
    suspend fun fetchAllLogsOfWaterIntake(): MutableList<FetchedLog>
    suspend fun deleteTheLog()
}