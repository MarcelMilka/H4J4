package com.example.h4j4.homeScreen.repository

import android.util.Log
import com.example.h4j4.homeScreen.HomeScreenInterface
import com.example.h4j4.homeScreen.enumClasses.WaterOrCreatine
import com.example.h4j4.homeScreen.viewState.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.coroutines.resume

class HomeScreenRepository : HomeScreenInterface {

    private val _firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _me = _firebase.collection("me")
    private val _weeklyIntakeOfWater = _me.document("Weekly intake of water")
    private val _weeklyIntakeOfCreatine = _me.document("Weekly intake of creatine")

    private val currentDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek
    private val currentDate = LocalDate.now()
    private val currentHour = LocalDateTime.now().hour
    private val currentMinute = LocalDateTime.now().minute
    override suspend fun checkWhatIsTracked(): WhatIsTracked {

        return suspendCoroutine { continuation ->

            _me.document("What is tracked")
                .get()
                .addOnSuccessListener {

                    it?.let {

                        val water = it.getBoolean("water")
                        val creatine = it.getBoolean("creatine")

                        if (water != null && creatine != null) {

                            Log.d("test of checkWhatIsTracked", "water = $water, creatine = $creatine")

                            continuation.resume(WhatIsTracked(water = water, creatine = creatine))
                        }
                    }
                }
        }
    }

    override suspend fun checkIfNewWeek() {

        if (currentDayOfWeek == DayOfWeek.MONDAY) {

            _weeklyIntakeOfWater
                .get()
                .addOnSuccessListener {

                    if (it != null) {

//                        monday is not necessary, because on monday there may be other value than 0
                        val tuesday = it.getString("tuesday")!!.toInt()
                        val wednesday = it.getString("wednesday")!!.toInt()
                        val thursday = it.getString("thursday")!!.toInt()
                        val friday = it.getString("friday")!!.toInt()
                        val saturday = it.getString("saturday")!!.toInt()
                        val sunday = it.getString("sunday")!!.toInt()

                        if (tuesday != 0 ||
                            wednesday != 0 ||
                            thursday != 0 ||
                            friday != 0 ||
                            saturday != 0 ||
                            sunday != 0
                        ) {

                            val weeklyIntakeOfWater = mapOf(
                                "monday" to "0",
                                "tuesday" to "0",
                                "wednesday" to "0",
                                "thursday" to "0",
                                "friday" to "0",
                                "saturday" to "0",
                                "sunday" to "0",
                            )


                            _weeklyIntakeOfWater.update(weeklyIntakeOfWater)

                            CoroutineScope(Dispatchers.IO).launch { loadData(null) }

                        } else {
                            CoroutineScope(Dispatchers.IO).launch { loadData(null) }
                        }
                    }
                }

            _weeklyIntakeOfCreatine
                .get()
                .addOnSuccessListener {

                    if (it != null) {

//                        monday is not necessary, because on monday there may be other value than 0
                        val tuesday = it.getString("tuesday")!!.toInt()
                        val wednesday = it.getString("wednesday")!!.toInt()
                        val thursday = it.getString("thursday")!!.toInt()
                        val friday = it.getString("friday")!!.toInt()
                        val saturday = it.getString("saturday")!!.toInt()
                        val sunday = it.getString("sunday")!!.toInt()

                        if (tuesday != 0 ||
                            wednesday != 0 ||
                            thursday != 0 ||
                            friday != 0 ||
                            saturday != 0 ||
                            sunday != 0
                        ) {

                            val weeklyIntakeOfCreatine = mapOf(
                                "monday" to "0",
                                "tuesday" to "0",
                                "wednesday" to "0",
                                "thursday" to "0",
                                "friday" to "0",
                                "saturday" to "0",
                                "sunday" to "0",
                            )


                            _weeklyIntakeOfCreatine.update(weeklyIntakeOfCreatine)

                            CoroutineScope(Dispatchers.IO).launch { loadData(null) }

                        } else {
                            CoroutineScope(Dispatchers.IO).launch { loadData(null) }
                        }
                    }
                }
        } else {
            loadData(null)
        }
    }

    override suspend fun loadData(whatIsTracked: WhatIsTracked?): MyUnit {

        val portionsOfWater = PortionsOfWater(0, 0, 0)
        val portionsOfCreatine = PortionsOfCreatine(0, 0, 0)
        val streak = Streak(0)
        val weeklyIntakeOfWater = WeeklyIntakeOfWater(0, 0, 0, 0, 0, 0, 0, 0)
        val weeklyIntakeOfCreatine = WeeklyIntakeOfCreatine(0, 0, 0, 0, 0, 0, 0,0)

        return suspendCoroutine { continuation ->

            _me
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

                            "Streak" -> {

                                val _streak = document.getString("streak")

                                if (_streak != null) {
                                    streak.streak = _streak.toInt()
                                }

                                else { return@forEach }

                                Log.d("streak", "${streak.streak}")
                            }

                            "Weekly intake of creatine" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.creatine) {
                                        weeklyIntakeOfCreatine.dailyGoal = document.getString("daily goal")?.toInt()!!
                                        weeklyIntakeOfCreatine.monday = document.getString("monday")?.toInt()!!
                                        weeklyIntakeOfCreatine.tuesday = document.getString("tuesday")?.toInt()!!
                                        weeklyIntakeOfCreatine.wednesday = document.getString("wednesday")?.toInt()!!
                                        weeklyIntakeOfCreatine.thursday = document.getString("thursday")?.toInt()!!
                                        weeklyIntakeOfCreatine.friday = document.getString("friday")?.toInt()!!
                                        weeklyIntakeOfCreatine.saturday = document.getString("saturday")?.toInt()!!
                                        weeklyIntakeOfCreatine.sunday = document.getString("sunday")?.toInt()!!
                                    }

                                    else {
                                        weeklyIntakeOfCreatine.dailyGoal = 0
                                        weeklyIntakeOfCreatine.monday = 0
                                        weeklyIntakeOfCreatine.tuesday = 0
                                        weeklyIntakeOfCreatine.wednesday = 0
                                        weeklyIntakeOfCreatine.thursday = 0
                                        weeklyIntakeOfCreatine.friday = 0
                                        weeklyIntakeOfCreatine.saturday = 0
                                        weeklyIntakeOfCreatine.sunday = 0
                                    }
                                }

                                else { return@forEach }

                                Log.d("weekly intake of creatine", "$weeklyIntakeOfCreatine")
                            }

                            "Weekly intake of water" -> {

                                if (whatIsTracked != null) {

                                    if (whatIsTracked.water) {
                                        weeklyIntakeOfWater.dailyGoal = document.getString("daily goal")?.toInt()!!
                                        weeklyIntakeOfWater.monday = document.getString("monday")?.toInt()!!
                                        weeklyIntakeOfWater.tuesday = document.getString("tuesday")?.toInt()!!
                                        weeklyIntakeOfWater.wednesday = document.getString("wednesday")?.toInt()!!
                                        weeklyIntakeOfWater.thursday = document.getString("thursday")?.toInt()!!
                                        weeklyIntakeOfWater.friday = document.getString("friday")?.toInt()!!
                                        weeklyIntakeOfWater.saturday = document.getString("saturday")?.toInt()!!
                                        weeklyIntakeOfWater.sunday = document.getString("sunday")?.toInt()!!
                                    }

                                    else {
                                        weeklyIntakeOfWater.dailyGoal = 0
                                        weeklyIntakeOfWater.monday = 0
                                        weeklyIntakeOfWater.tuesday = 0
                                        weeklyIntakeOfWater.wednesday = 0
                                        weeklyIntakeOfWater.thursday = 0
                                        weeklyIntakeOfWater.friday = 0
                                        weeklyIntakeOfWater.saturday = 0
                                        weeklyIntakeOfWater.sunday = 0
                                    }
                                }

                                else { return@forEach }

                                Log.d("weekly intake of water", "$weeklyIntakeOfWater")
                            }
                        }
                    }

                    continuation.resume(
                        MyUnit(
                            streak = streak,
                            weeklyIntakeOfWater = weeklyIntakeOfWater,
                            weeklyIntakeOfCreatine = weeklyIntakeOfCreatine,
                            portionsOfWater = portionsOfWater,
                            portionsOfCreatine = portionsOfCreatine
                        )
                    )
                }
        }
    }

    override suspend fun loadChanges(homeScreenViewState: HomeScreenViewState.LoadedSuccessfully) : kotlinx.coroutines.flow.Flow<LoadChangesUnit> {

        return callbackFlow {

            val weeklyWaterIntake = _weeklyIntakeOfWater
                .addSnapshotListener { value, error ->

                    if (value != null && error == null) {

                        when (homeScreenViewState.whatIsTracked) {

                            WhatIsTracked(water = true, creatine = false) -> {

                                val dailyGoal = value.getString("daily goal")?.toInt()!!
                                val monday = value.getString("monday")?.toInt()!!
                                val tuesday = value.getString("tuesday")?.toInt()!!
                                val wednesday = value.getString("wednesday")?.toInt()!!
                                val thursday = value.getString("thursday")?.toInt()!!
                                val friday = value.getString("friday")?.toInt()!!
                                val saturday = value.getString("saturday")?.toInt()!!
                                val sunday = value.getString("sunday")?.toInt()!!

                                val weeklyWaterIntake = WeeklyIntakeOfWater(dailyGoal, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
                                trySend(LoadChangesUnit(weeklyIntakeOfWater = weeklyWaterIntake, weeklyIntakeOfCreatine = null))
                            }

                            WhatIsTracked(water = false, creatine = true) -> {

                                val dailyGoal = value.getString("daily goal")?.toInt()!!
                                val monday = value.getString("monday")?.toInt()!!
                                val tuesday = value.getString("tuesday")?.toInt()!!
                                val wednesday = value.getString("wednesday")?.toInt()!!
                                val thursday = value.getString("thursday")?.toInt()!!
                                val friday = value.getString("friday")?.toInt()!!
                                val saturday = value.getString("saturday")?.toInt()!!
                                val sunday = value.getString("sunday")?.toInt()!!

                                val weeklyWaterIntake = WeeklyIntakeOfWater(dailyGoal, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
                                trySend(LoadChangesUnit(weeklyIntakeOfWater = weeklyWaterIntake, weeklyIntakeOfCreatine = null))
                            }
                        }
                    }

                    else {
                        return@addSnapshotListener
                    }
                }

            awaitClose { weeklyWaterIntake.remove() }
        }
    }

    override suspend fun addAnotherPortionOfWaterOrCreatine(waterOrCreatine: WaterOrCreatine, currentAmount: String, amountToInrease: String) {

        val sum = (currentAmount.toInt() + amountToInrease.toInt()).toString()

        var scheme = mutableMapOf<String, String>()
        scheme.put(currentDate.toString(), sum)

        when (waterOrCreatine) {

            WaterOrCreatine.water -> {
                _weeklyIntakeOfWater.set(scheme)
            }
            WaterOrCreatine.creatine -> {
                _weeklyIntakeOfCreatine.set(scheme)
            }
        }
    }

    override suspend fun addNewPortionOfWaterOrCreatine() {
        TODO("Not yet implemented")
    }
}