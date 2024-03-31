package com.example.h4j4.user.repository

import com.example.h4j4.user.viewState.UserUnit

interface UserRepositoryInterface {

    suspend fun fetchData(): UserUnit
}