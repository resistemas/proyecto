package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Vendedor(

    @SerializedName("id")
    var id : Int?    = null,

    @SerializedName("nombresApellidos")
    var nombresApellidos : String? = null,

    @SerializedName("photo")
    var photo : String? = null

)
