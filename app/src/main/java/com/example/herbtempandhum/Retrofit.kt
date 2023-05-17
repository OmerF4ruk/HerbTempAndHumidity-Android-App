package com.example.herbtempandhum

import com.example.herbtempandhum.api.DataApi
import com.example.herbtempandhum.api.DeviceDataApi
import com.example.herbtempandhum.api.UserApi
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private const val BASE_URL = "http://192.168.1.7:3500"
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
    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}