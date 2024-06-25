package com.example.proyecto.Model.Header

import com.example.proyecto.Model.Favorito
import com.google.gson.annotations.SerializedName

data class HeaderFavorito(
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("status")
    var status : Boolean? = null,

    @SerializedName("data")
    var data : ArrayList<Favorito> = arrayListOf()
)
