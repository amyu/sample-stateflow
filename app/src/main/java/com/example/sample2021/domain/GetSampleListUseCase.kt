package com.example.sample2021.domain

import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject


class GetSampleListUseCase
@Inject constructor() {
    suspend fun execute(): List<Sample> {
        delay(2000)
        return (0..20).map {
            Sample(
                id = SampleId(UUID.randomUUID().toString()),
                firstName = "firstName$it",
                lastName = "lastName$it"
            )
        }
    }
}