package com.example.proyecto.Network.Response

import com.example.proyecto.Model.Productos
import com.google.gson.annotations.SerializedName

data class ProductosResponse(
    @SerializedName("data")
    var data : List<Productos>
)
