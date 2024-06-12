package com.example.proyecto.Network

import com.example.proyecto.Model.Header.HeadMasVendidos
import com.example.proyecto.Model.Header.HeadProductos
import com.example.proyecto.Model.Header.HeadVerificarAuth
import com.example.proyecto.Model.Header.HeaderLogin
import com.example.proyecto.Model.Login
import com.example.proyecto.Model.Productos
import com.example.proyecto.Model.Usuario
import com.example.proyecto.Network.Response.NuevosResponse
import com.example.proyecto.Network.Response.ProductosResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface WebService {
//    RUTAS DE LOGIN
    @GET("auth/login")
    fun verficarAuth() : Call<HeadVerificarAuth>

    @Headers("content-type: application/json")
    @POST("auth/login")
    fun autenticacionAuth(@Body request : Login) : Call<HeaderLogin>

//    RUTA DE PRODUCTOS
    @GET("producto/masvendido")
    fun masVendidos(): Call<HeadMasVendidos>

    @GET("producto/nuevos")
    fun nuevos(): Call<HeadProductos>

    @GET("producto/{id}/detalle")
    fun productoDetalle( @Path(value = "id", encoded = true) string: String ): Call<HeadProductos>

    @GET("producto/{categoria}/relacionado")
    fun productoDetalleRelacionado( @Path(value = "categoria", encoded = true) string: String ): Call<HeadProductos>

    @POST
    suspend fun addProducto(@Body Productos : Productos) : Call<*>

}