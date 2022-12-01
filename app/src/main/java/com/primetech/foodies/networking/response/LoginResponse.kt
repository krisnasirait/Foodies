package com.primetech.foodies.networking.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status" ) var status : String? = null,
    @SerializedName("data"   ) var data   : Data?   = Data()
    )

data class Data (

    @SerializedName("user"        ) var user        : User?   = User(),
    @SerializedName("accessToken" ) var accessToken : String? = null

)

data class User (

    @SerializedName("id"         ) var id         : Int?    = null,
    @SerializedName("password"   ) var password   : String? = null,
    @SerializedName("name"       ) var name       : String? = null,
    @SerializedName("address"    ) var address    : String? = null,
    @SerializedName("phone"      ) var phone      : String? = null,
    @SerializedName("birthdate"  ) var birthdate  : String? = null,
    @SerializedName("birthplace" ) var birthplace : String? = null,
    @SerializedName("id_number"  ) var idNumber   : String? = null,
    @SerializedName("gender"     ) var gender     : String? = null,
    @SerializedName("id_member"  ) var idMember   : String? = null,
    @SerializedName("role"       ) var role       : String? = null,
    @SerializedName("createdAt"  ) var createdAt  : String? = null,
    @SerializedName("updatedAt"  ) var updatedAt  : String? = null

)