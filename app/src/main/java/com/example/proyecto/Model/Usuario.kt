package com.example.proyecto.Model

import com.google.gson.annotations.SerializedName

data class Usuario(

    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("rol_id")
    var rol_id : Int? = null,

    @SerializedName("codigo")
    var codigo : String? = null,

    @SerializedName("nombresApellidos")
    var nombresApellidos : String? = null,

    @SerializedName("correoElectronico")
    var correoElectronico : String? = null,

    @SerializedName("photo")
    var photo : String? = null,

    @SerializedName("usuario")
    var usuario : String? = null,

    @SerializedName("estado")
    var estado : String? = null,

    @SerializedName("roles")
    var roles : Rol

)
