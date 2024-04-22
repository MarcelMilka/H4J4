package com.example.h4j4.user.repository

import android.util.Log
import com.example.h4j4.homeScreen.viewState.PortionsOfCreatine
import com.example.h4j4.homeScreen.viewState.PortionsOfWater
import com.example.h4j4.homeScreen.viewState.WhatIsTracked
import com.example.h4j4.user.dataClasses.UserSettingsChange
import com.example.h4j4.user.viewState.DailyAmountOfCreatineToIngest
import com.example.h4j4.user.viewState.DailyAmountOfWaterToIngest
import com.example.h4j4.user.viewState.UserUnit
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepository: UserRepositoryInterface {

    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val me = firebase.collection("me")

    override suspend fun fetchData(): UserUnit {

        var whatIsTracked = WhatIsTracked(water = false, creatine = false)
        var dailyAmountOfWaterToIngest = DailyAmountOfWaterToIngest(dailyAmountOfWaterToIngest = "0")
        var portionsOfWater = PortionsOfWater(firstPortion = 0, secondPortion = 0, thirdPortion = 0)
        var dailyAmountOfCreatineToIngest = DailyAmountOfCreatineToIngest("0")
        var portionsOfCreatine = PortionsOfCreatine(firstPortion = 0, secondPortion = 0, thirdPortion = 0)

        return suspendCoroutine { continuation ->

            me.get()
                .addOnSuccessListener {

                    val allDocuments = it.documents

                    allDocuments.forEach { document ->

                        when (document.id) {

                            "What is tracked" -> {

                                val water = document.getBoolean("water")
                                val creatine = document.getBoolean("creatine")

                                if (water != null && creatine != null) {
                                    whatIsTracked = WhatIsTracked(water = water, creatine = creatine)
                                }

                                else {
                                    return@forEach
                                }
                            } // okay

                            "Weekly intake of water" -> {

                                val daily = document.getString("daily goal")

                                if (daily != null) {
                                    dailyAmountOfWaterToIngest = DailyAmountOfWaterToIngest(dailyAmountOfWaterToIngest = daily)
                                }

                                else {
                                    return@forEach
                                }
                            } // okay

                            "Portions of water" -> {

                                val firstPortion = document.getString("first portion")?.toInt()!!
                                val secondPortion = document.getString("second portion")?.toInt()!!
                                val thirdPortion = document.getString("third portion")?.toInt()!!

                                if (firstPortion != null && secondPortion != null && thirdPortion != null) {
                                    portionsOfWater = PortionsOfWater(firstPortion, secondPortion, thirdPortion)
                                }

                                else {
                                    return@forEach
                                }
                            }

                            "Weekly intake of creatine" -> {

                                val daily = document.getString("daily goal")

                                if (daily != null) {
                                    dailyAmountOfCreatineToIngest = DailyAmountOfCreatineToIngest(dailyAmountOfCreatineToIngest = daily)
                                }

                                else {
                                    return@forEach
                                }
                            } // okay

                            "Portions of creatine" -> {

                                val firstPortion = document.getString("first portion")?.toInt()!!
                                val secondPortion = document.getString("second portion")?.toInt()!!
                                val thirdPortion = document.getString("third portion")?.toInt()!!

                                if (firstPortion != null && secondPortion != null && thirdPortion != null) {
                                    portionsOfCreatine = PortionsOfCreatine(firstPortion, secondPortion, thirdPortion)
                                }

                                else {
                                    return@forEach
                                }
                            }
                        }
                    }

                    continuation.resume(UserUnit(whatIsTracked, dailyAmountOfWaterToIngest, portionsOfWater, dailyAmountOfCreatineToIngest, portionsOfCreatine))
                }
        }
    }

    override suspend fun updateValues(changes: MutableList<UserSettingsChange>) {

        changes.forEach { userSettingsChange ->

            me.document(userSettingsChange.nameOfSubcollection).update(userSettingsChange.nameOfField, userSettingsChange.newValue.toString())
                .addOnSuccessListener { Log.d("saving changes", "saved successfully") }
                .addOnFailureListener { Log.d("saving changes", "$it") }
        }
    }
}