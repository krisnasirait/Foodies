package com.instances.resturent_finder.networking

import com.primetech.foodies.networking.request.LoginRequest
import com.primetech.foodies.networking.ApiListener
import com.primetech.foodies.networking.NetworkingHelper
import com.primetech.foodies.networking.response.LoginResponse


class NetworkingModel : NetworkingHelper() {

    fun excLoginUserApi(
        loginRequest: LoginRequest,
        apiListener: ApiListener<LoginResponse>
    ) {
        loginUser(loginRequest, apiListener = apiListener)
    }

}