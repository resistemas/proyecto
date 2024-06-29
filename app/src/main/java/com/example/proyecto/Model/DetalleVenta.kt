package com.example.proyecto.Model

import android.hardware.biometrics.BiometricManager.Strings
import com.google.gson.annotations.SerializedName

data class DetalleVenta(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("venta_id")
    var ventaId : Int? = null,

    @SerializedName("producto_id")
    var productoId : Int? = null,

    @SerializedName("cantidad")
    var cantidad : Int? = null,

    @SerializedName("total")
    var total : Float? = null,

    @SerializedName("estado")
    var estado : String? = null,

    @SerializedName("producto")
    var producto : Productos? = Productos()
)
