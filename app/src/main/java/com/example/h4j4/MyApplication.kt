package com.example.h4j4

import android.app.Application
import com.example.h4j4.manualDI.AppContainer

class MyApplication: Application() {

    val container = AppContainer()
}