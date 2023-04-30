package com.example.herbtempandhum.data

import retrofit2.http.GET

interface DeviceDataApi {

    @GET("/devices/getByUserId")
    suspend fun getDevices(): List<Device>

}