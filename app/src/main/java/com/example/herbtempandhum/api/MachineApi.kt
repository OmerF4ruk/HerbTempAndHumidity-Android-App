package com.example.herbtempandhum.api

import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.data.Machine.PredData
import com.example.herbtempandhum.data.Machine.ResultPredict
import com.example.herbtempandhum.data.NewDevice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MachineApi {
    @GET("/data/predict/{id}")
    fun Predict(@Path("id") id:String): Call<ResultPredict>


}