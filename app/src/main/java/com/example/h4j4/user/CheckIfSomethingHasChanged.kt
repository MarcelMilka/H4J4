package com.example.h4j4.user

data class CheckIfSomethingHasChanged(
    var waterIsTracked: Boolean,
    var creatineIsTracked: Boolean,

    var dailyGoalOfIngestionOfWater: Int,
    var dailyGoalOfIngestionOfCreatine: Int,

    var firstPortionOfWater: Int,
    var secondPortionOfWater: Int,
    var thirdPortionOfWater: Int,

    var firstPortionOfCreatine: Int,
    var secondPortionOfCreatine: Int,
    var thirdPortionOfCreatine: Int
)
