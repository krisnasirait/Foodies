package com.instances.resturent_finder.networking

import com.instances.resturent_finder.networking.request.LoginRequest
import com.instances.resturent_finder.networking.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}