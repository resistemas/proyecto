package com.example.proyecto.Model.Header

import com.example.proyecto.Model.Productos
import com.example.proyecto.Model.Usuario
import com.google.gson.annotations.SerializedName

data class HeadVerificarAuth(
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("status")
    var status : Boolean? = null,

    @SerializedName("data")
    var data : ArrayList<Usuario> = arrayListOf()
)
