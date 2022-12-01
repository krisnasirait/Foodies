package com.instances.resturent_finder.networking.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("phone"    ) var phone    : String? = null,
    @SerializedName("password" ) var password : String? = null
    )