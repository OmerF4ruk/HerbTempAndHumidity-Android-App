package com.example.herbtempandhum.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {

    @GET("/data/{id}")
    fun getData(@Path("id") id:Int): Call<List<Data>>
}