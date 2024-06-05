package com.example.proyecto.Network.Response

import com.example.proyecto.Model.MasVendidos
import com.example.proyecto.Model.Productos
import com.example.proyecto.Model.Relacionados
import com.google.gson.annotations.SerializedName

data class ProductosResponse(
    @SerializedName("data")
    var data : List<Productos>
)

data class MasVedidosResponse(
    @SerializedName("data")
    var data : List<MasVendidos>
)

data class RelacionadosResponse(
    @SerializedName("data")
    var data : List<Relacionados>
)
