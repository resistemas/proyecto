package com.example.proyecto.Model.Header

import com.example.proyecto.Model.Venta
import com.google.gson.annotations.SerializedName

data class HeaderVenta(
    @SerializedName("message" )
    var message : String? = null,

    @SerializedName("status")
    var status  : Boolean? = null,

    @SerializedName("data")
    var data : ArrayList<Venta> = arrayListOf()
)
