package com.primetech.foodies.networking

import com.primetech.foodies.networking.request.LoginRequest
import com.primetech.foodies.networking.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}