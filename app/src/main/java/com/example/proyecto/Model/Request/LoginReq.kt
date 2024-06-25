package com.example.proyecto.Model.Request

import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("email")
    var email : String? = null,

    @SerializedName("password")
    var password : String? = null,
)
