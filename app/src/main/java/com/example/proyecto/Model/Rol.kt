package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Rol(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("rol")
    var rol : String? = null,

    @SerializedName("estado")
    var estado : String? = null
)
