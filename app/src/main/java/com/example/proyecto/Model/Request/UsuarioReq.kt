package com.example.proyecto.Model.Request

import com.google.gson.annotations.SerializedName

data class UsuarioReq(
    @SerializedName("rol_id")
    var rolId : Int? = null,

    @SerializedName("nombresApellidos")
    var nombresApellidos : String? = null,

    @SerializedName("correoElectronico")
    var correoElectronico : String? = null,

    @SerializedName("photo")
    var photo : String? = null,

    @SerializedName("usuario")
    var usuario : String? = null,

    @SerializedName("password")
    var password : String? = null,

    @SerializedName("estado")
    var estado : String? = null
)
