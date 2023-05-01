package com.example.herbtempandhum.api

import com.example.herbtempandhum.data.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {

    @GET("/data/{id}")
    fun getData(@Path("id") id:String): Call<List<Data>>
}