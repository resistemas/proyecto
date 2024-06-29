package com.example.proyecto.Model.Request

import com.google.gson.annotations.SerializedName

data class CategoriaReq(
    @SerializedName("cateogria")
    var cateogria : String? = null,

    @SerializedName("estado")
    var estado : String? = null
)
