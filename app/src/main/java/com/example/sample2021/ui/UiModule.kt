package com.example.sample2021.ui

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@AssistedModule
@InstallIn(SingletonComponent::class)
@Module
abstract class UiModule