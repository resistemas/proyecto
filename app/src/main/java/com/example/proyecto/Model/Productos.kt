package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Productos(
    @SerializedName("id")
    var id : Int,

    @SerializedName("usuario_id")
    var usuario_id : Int,

    @SerializedName("categoria_id")
    var categoria_id : Int,

    @SerializedName("codigo")
    var codigo : String,

    @SerializedName("producto")
    var producto : String,

    @SerializedName("descripcion")
    var descripcion : String,

    @SerializedName("photo_video")
    var photo_video : String,

    @SerializedName("precio")
    var precio : String,

    @SerializedName("estado")
    var estado : String,
)
