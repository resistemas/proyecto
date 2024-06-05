package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Categoria(

    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("categoria")
    var categoria : String? = null,

    @SerializedName("estado")
    var estado : String? = null

)
