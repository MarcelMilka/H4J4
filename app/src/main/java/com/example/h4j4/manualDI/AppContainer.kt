package com.example.h4j4.manualDI

import com.example.h4j4.homeScreen.HomeScreenInterface
import com.example.h4j4.homeScreen.repository.HomeScreenRepository

class AppContainer {

    val repository: HomeScreenInterface = HomeScreenRepository()
}