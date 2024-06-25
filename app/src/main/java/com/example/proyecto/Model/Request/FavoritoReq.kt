package com.example.proyecto.Model.Request

import com.google.gson.annotations.SerializedName

data class FavoritoReq(
    @SerializedName("cliente_id")
    var cliente_id : String? = null,

    @SerializedName("producto_id")
    var producto_id : String? = null,

    @SerializedName("estado")
    var estado : String? = null
)
