package com.example.proyecto.Domain

data class MasVendidos(
    val id: Int,
    val usuario_id: Int,
    val categoria_id: Int,
    val codigo: String,
    val producto: String,
    val descripcion: String,
    val photo_video: String,
    val precio: Double,
    val estado: String,
//    val vendedor: Vendedor,
//    val categoria: Categoria
)