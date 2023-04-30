package com.example.herbtempandhum.data

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private const val BASE_URL = "https://www.MYAPI.com/"

    private val retrofit = retrofit2.Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api: DeviceDataApi by lazy {
        retrofit.create(DeviceDataApi::class.java)
    }


}