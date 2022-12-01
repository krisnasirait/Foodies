package com.instances.resturent_finder.networking

import com.instances.resturent_finder.networking.request.LoginRequest
import com.instances.resturent_finder.networking.response.LoginResponse


class NetworkingModel : NetworkingHelper() {

    fun excLoginUserApi(
        loginRequest: LoginRequest,
        apiListener: ApiListener<LoginResponse>
    ) {
        loginUser(loginRequest, apiListener = apiListener)
    }

}