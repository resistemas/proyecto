package com.example.proyecto.Model.Request

import com.google.gson.annotations.SerializedName

data class VentaReq(
    @SerializedName("artesano_id")
    var artesanoId : String? = null,

    @SerializedName("cliente_id")
    var clienteId : String? = null,

    @SerializedName("ciudad")
    var ciudad : String? = null,

    @SerializedName("direccion")
    var direccion : String? = null,

    @SerializedName("producto_id")
    var productoId : String? = null,

    @SerializedName("cantidad")
    var cantidad : String? = null,

    @SerializedName("total")
    var total : String? = null,

    @SerializedName("estado")
    var estado : String? = null,
)
