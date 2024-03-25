package com.example.h4j4.dependencyInjection

import com.example.h4j4.homeScreen.repository.HomeScreenInterface
import com.example.h4j4.homeScreen.repository.HomeScreenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Module {

    @Binds
    abstract fun provideHomeScreenRepository(
        provided: HomeScreenRepository
    ): HomeScreenInterface
}