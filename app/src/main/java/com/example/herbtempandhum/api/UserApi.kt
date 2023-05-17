package com.example.herbtempandhum.api

import com.example.herbtempandhum.data.Data
import com.example.herbtempandhum.data.LoginRequest
import com.example.herbtempandhum.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("/user/login")
    fun login(@Body loginRequest: LoginRequest): Call<User>
}