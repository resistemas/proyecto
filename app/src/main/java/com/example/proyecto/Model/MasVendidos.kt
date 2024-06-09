package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class MasVendidos(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("venta_id")
    var ventaId : Int? = null,

    @SerializedName("producto_id")
    var productoId : Int? = null,

    @SerializedName("cantidad")
    var cantidad : Int? = null,

    @SerializedName("total")
    var total : String? = null,

    @SerializedName("producto")
    var producto : Productos
)