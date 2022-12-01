package com.instances.resturent_finder.networking

import com.instances.resturent_finder.networking.RetrofitClient.getApiEndpointImpl
import com.instances.resturent_finder.networking.request.LoginRequest
import com.instances.resturent_finder.networking.response.LoginResponse

// this class help to call get the retrofit instance in networking model class where api is calling
abstract class NetworkingHelper {

    private val apiService = getApiEndpointImpl()

    fun loginUser(loginRequest: LoginRequest, apiListener: ApiListener<LoginResponse>) {
        val call = apiService.loginUser(loginRequest)
        RetrofitClient.executeApi(call, apiListener)
    }

}
