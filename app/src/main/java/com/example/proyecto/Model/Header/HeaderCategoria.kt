package com.example.proyecto.Model.Header

import com.example.proyecto.Model.Categoria
import com.google.gson.annotations.SerializedName

data class HeaderCategoria(
    @SerializedName("message" )
    var message : String? = null,

    @SerializedName("status")
    var status  : Boolean? = null,

    @SerializedName("data")
    var data : ArrayList<Categoria> = arrayListOf()
)
