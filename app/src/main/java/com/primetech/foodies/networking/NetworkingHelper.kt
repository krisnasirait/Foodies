package com.primetech.foodies.networking

import com.primetech.foodies.networking.RetrofitClient.getApiEndpointImpl
import com.primetech.foodies.networking.request.LoginRequest
import com.primetech.foodies.networking.response.LoginResponse

// this class help to call get the retrofit instance in networking model class where api is calling
abstract class NetworkingHelper {

    private val apiService = getApiEndpointImpl()

    fun loginUser(loginRequest: LoginRequest, apiListener: ApiListener<LoginResponse>) {
        val call = apiService.loginUser(loginRequest)
        RetrofitClient.executeApi(call, apiListener)
    }

}
