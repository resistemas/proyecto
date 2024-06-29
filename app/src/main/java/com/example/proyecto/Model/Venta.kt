package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Venta(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("artesano_id")
    var artesanoId : Int? = null,

    @SerializedName("cliente_id")
    var clienteId : Int? = null,

    @SerializedName("codigo")
    var codigo : String? = null,

    @SerializedName("ciudad")
    var ciudad : String? = null,

    @SerializedName("direccion")
    var direccion : String? = null,

    @SerializedName("estado")
    var estado : String? = null,

    @SerializedName("artesano")
    var artesano : Usuario? = Usuario(),

    @SerializedName("cliente")
    var cliente  : Cliente?  = Cliente(),

    @SerializedName("detalle")
    var detalle : DetalleVenta?  = DetalleVenta()
)
