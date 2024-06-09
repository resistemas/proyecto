package com.example.proyecto.Network.Response

import com.example.proyecto.Model.Header.HeadMasVendidos
import com.example.proyecto.Model.Header.HeadProductos
import com.example.proyecto.Model.Productos
import com.google.gson.annotations.SerializedName

data class ProductosResponse(
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("status")
    var status : Boolean? = null,

    @SerializedName("data")
    var data : List<Productos>
)

data class MasVedidosResponse(
    @SerializedName("data")
    var data : List<HeadMasVendidos>
)

data class NuevosResponse(
    @SerializedName("data")
    var data : List<HeadProductos>
)
