package com.example.herbtempandhum.data

import java.time.LocalDate

data class Device(
    val airHumidity: Double,
    val airTemperature: Double,
    val date: LocalDate
)