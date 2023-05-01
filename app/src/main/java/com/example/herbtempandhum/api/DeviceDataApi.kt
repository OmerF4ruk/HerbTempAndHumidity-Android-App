package com.example.herbtempandhum.api

import com.example.herbtempandhum.data.Device
import retrofit2.Call
import retrofit2.http.*

interface DeviceDataApi {



    @GET("/device/getByUserId/{id}")
    fun getDevices(@Path("id") id:Int): Call<List<Device>>

}