package com.example.herbtempandhum.api

import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.data.LoginRequest
import com.example.herbtempandhum.data.NewDevice
import com.example.herbtempandhum.data.User
import retrofit2.Call
import retrofit2.http.*

interface DeviceDataApi {

    @POST("/device/addDevice")
    fun addDevice(@Body device: NewDevice): Call<Device>

    @GET("/device/getByUserId/{id}")
    fun getDevices(@Path("id") id:Int): Call<List<Device>>

}