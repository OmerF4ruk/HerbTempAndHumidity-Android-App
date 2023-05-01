package com.example.herbtempandhum.data

import java.time.LocalDate
import java.util.Date
import java.util.TimeZone

data class Data(
    val device_id:Int,
    val air_humidity: String,
    val air_temperature: String,
    val soil_humidity: String,
    val soil_temperature: String,
    val date:Date,
    val time:String
)