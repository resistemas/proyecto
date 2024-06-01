package com.example.proyecto.Domain

data class Vendedor (
    val id : Int,
    val rol_id : Int,
    val codigo : String,
    val nombresApellidos : String,
    val correoElectronico : String,
    val photo : String,
    val usuario : String
)