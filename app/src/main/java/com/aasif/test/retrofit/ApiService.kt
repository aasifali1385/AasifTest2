package com.aasif.test.retrofit

import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.LoginSignResponse
import com.aasif.test.data.Register
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {



    @POST("api/login")
    suspend fun login(@Body login: Login): LoginSignResponse

    @POST("api/register")
    suspend fun register(@Body register: Register) : LoginSignResponse


    @GET("categories")
    suspend fun getCategories(): List<FoodsCate>



}