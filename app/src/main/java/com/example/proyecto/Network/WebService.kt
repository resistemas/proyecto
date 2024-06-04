package com.example.proyecto.Network

import com.example.proyecto.Network.Response.ProductosResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("producto/masvendido")
    suspend fun masVendidos(

    ): Response<ProductosResponse>

    @GET("producto/relacionados")
    suspend fun relacionados(

    ): Response<ProductosResponse>

}