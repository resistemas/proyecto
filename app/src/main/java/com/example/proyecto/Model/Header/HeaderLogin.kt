package com.example.proyecto.Model.Header

import com.example.proyecto.Model.MasVendidos
import com.google.gson.annotations.SerializedName

data class HeaderLogin(
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("status")
    var status : Boolean? = null,

    @SerializedName("token")
    var token : String? = null
)
