package com.aasif.test

import android.util.Log
import com.aasif.test.data.Login
import com.aasif.test.data.Register
import com.aasif.test.retrofit.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun login(login: Login) = apiService.login(login)

    suspend fun register(register:Register) = apiService.register(register)


    suspend fun getCategories() = apiService.getCategories()

}