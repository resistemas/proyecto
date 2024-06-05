package com.example.proyecto.Network

import com.example.proyecto.Model.Productos
import com.example.proyecto.Network.Response.MasVedidosResponse
import com.example.proyecto.Network.Response.ProductosResponse
import com.example.proyecto.Network.Response.RelacionadosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("producto/masvendido")
    suspend fun masVendidos(): Response<MasVedidosResponse>

    @GET("producto/relacionados")
    suspend fun relacionados(): Response<RelacionadosResponse>

    @GET("producto/{id}/detalle")
    suspend fun productoDetalle( @Path(value = "id", encoded = true) string: String ): Response<ProductosResponse>

    @GET("producto/{categoria}/relacionado")
    suspend fun productoDetalleRelacionado( @Path(value = "categoria", encoded = true) string: String ): Response<RelacionadosResponse>

}