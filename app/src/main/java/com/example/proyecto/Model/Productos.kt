package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Productos(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("usuario_id")
    var usuarioId : Int? = null,

    @SerializedName("categoria_id")
    var categoriaId : Int? = null,

    @SerializedName("codigo")
    var codigo : String? = null,

    @SerializedName("producto")
    var producto : String? = null,

    @SerializedName("descripcion")
    var descripcion : String? = null,

    @SerializedName("photo_video")
    var photo_video : String? = null,

    @SerializedName("precio")
    var precio : String? = null,

    @SerializedName("estado")
    var estado : String? = null,

    @SerializedName("vendedor")
    var vendedor : Vendedor? = Vendedor(),

    @SerializedName("categoria")
    var categoria : Categoria? = Categoria(),
)
