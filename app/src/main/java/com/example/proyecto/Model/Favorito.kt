package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Favorito(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("cliente_id")
    var clienteId : Int? = null,

    @SerializedName("producto_id")
    var productoId : Int? = null,

    @SerializedName("estado")
    var estado : String?  = null,

    @SerializedName("cliente")
    var cliente  : Cliente?  = Cliente(),

    @SerializedName("producto")
    var producto   : Productos? = Productos()
)
