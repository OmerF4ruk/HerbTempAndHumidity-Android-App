package com.example.herbtempandhum

import com.example.herbtempandhum.api.DataApi
import com.example.herbtempandhum.api.DeviceDataApi
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private const val BASE_URL = "http://192.168.1.4:3500"
    private val retrofit = retrofit2.Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val deviceApi: DeviceDataApi by lazy {
        retrofit.create(DeviceDataApi::class.java)
    }

    val dataApi: DataApi by lazy {
        retrofit.create(DataApi::class.java)
    }

}