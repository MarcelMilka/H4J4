package com.example.h4j4.user.repository

import android.util.Log
import com.example.h4j4.homeScreen.repository.HomeScreenRepository
import com.example.h4j4.homeScreen.viewState.*
import com.example.h4j4.user.viewState.DailyIngestionOfCreatine
import com.example.h4j4.user.viewState.DailyIngestionOfWater
import com.example.h4j4.user.viewState.UserUnit
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepository: HomeScreenRepository(), UserRepositoryInterface {

    val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    val me = firebase.collection("me")



    override suspend fun fetchData(whatIsTracked: WhatIsTracked): UserUnit {

        val portionsOfWater = PortionsOfWater(0, 0, 0)
        val portionsOfCreatine = PortionsOfCreatine(0, 0, 0)
        val dailyIngestionOfWater = DailyIngestionOfWater(0)
        val dailyIngestionOfCreatine = DailyIngestionOfCreatine(0)

        return suspendCoroutine { continuation ->

            me
                .get()
                .addOnSuccessListener { availableDocuments ->

                    availableDocuments.documents.forEach { document ->

                        when (document.id) {
                            "Portions of creatine" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.creatine) {

                                        portionsOfCreatine.firstPortion = document.getString("first portion")?.toInt()!!
                                        portionsOfCreatine.secondPortion = document.getString("second portion")?.toInt()!!
                                        portionsOfCreatine.thirdPortion = document.getString("third portion")?.toInt()!!
                                    }

                                    else {
                                        portionsOfCreatine.firstPortion = 0
                                        portionsOfCreatine.firstPortion = 0
                                        portionsOfCreatine.firstPortion = 0
                                    }

                                    Log.d("creatine", "$portionsOfCreatine")
                                }

                                else { return@forEach }
                            }

                            "Portions of water" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.water) {

                                        portionsOfWater.firstPortion = document.getString("first portion")?.toInt()!!
                                        portionsOfWater.secondPortion = document.getString("second portion")?.toInt()!!
                                        portionsOfWater.thirdPortion = document.getString("third portion")?.toInt()!!
                                    }

                                    else {
                                        portionsOfWater.firstPortion = 0
                                        portionsOfWater.firstPortion = 0
                                        portionsOfWater.firstPortion = 0
                                    }
                                    Log.d("water", "$portionsOfWater")
                                }

                                else { return@forEach }
                            }

                            "Weekly intake of creatine" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.creatine) {
                                        dailyIngestionOfCreatine.dailyIngestionOfCreatine = document.getString("daily goal")?.toInt()!!
                                    }
                                }

                                else { return@forEach }
                            }

                            "Weekly intake of water" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.water) {
                                        dailyIngestionOfWater.dailyIngestionOfWater = document.getString("daily goal")?.toInt()!!
                                    }
                                }

                                else { return@forEach }
                            }
                        }
                    }

                    continuation.resume(
                        UserUnit(whatIsTracked, dailyIngestionOfWater, dailyIngestionOfCreatine, portionsOfWater, portionsOfCreatine)
                    )
                }
        }
    }

    override suspend fun checkWhatIsTracked(): WhatIsTracked {
        return super.checkWhatIsTracked()
    }

    override suspend fun changeOrAddPortion() {
        TODO("Not yet implemented")
    }

    override suspend fun changeDailyGoal() {
        TODO("Not yet implemented")
    }

}