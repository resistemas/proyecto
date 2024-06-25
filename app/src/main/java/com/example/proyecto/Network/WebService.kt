package com.example.proyecto.Network

import com.example.proyecto.Model.Header.HeadMasVendidos
import com.example.proyecto.Model.Header.HeadProductos
import com.example.proyecto.Model.Header.HeadVerificarAuth
import com.example.proyecto.Model.Header.HeaderFavorito
import com.example.proyecto.Model.Header.HeaderLogin
import com.example.proyecto.Model.Header.HeaderUsuario
import com.example.proyecto.Model.Request.LoginReq
import com.example.proyecto.Model.Productos
import com.example.proyecto.Model.Request.FavoritoReq
import com.example.proyecto.Model.Request.UsuarioReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
//    RUTAS DE LOGIN
    @Headers("content-type: application/json")
    @GET("auth/user")
    fun verficarAuth() : Call<HeadVerificarAuth>

    @Headers("content-type: application/json")
    @POST("auth/login")
    fun autenticacionAuth(@Body request : LoginReq) : Call<HeaderLogin>

//    RUTAS DE USUARIOS
    @GET("usuario/lista")
    fun listadoUsuarios() : Call<HeaderUsuario>

    @Headers("content-type: application/json")
    @POST("usuario")
    fun storeUsuario(@Body request : UsuarioReq) : Call<HeaderUsuario>

    @Headers("content-type: application/json")
    @PUT("usuario/{usuario}/update")
    fun putUsuario(@Path(value = "usuario", encoded = true) String : String, @Body request : UsuarioReq) : Call<HeaderUsuario>

    @DELETE("fusuario/{usuario}/destroy")
    fun destroyUsuario(@Path(value = "usuario", encoded = true) String : String) : Call<HeaderUsuario>

    @GET("usuario/{usuario}/detalle")
    fun getUsuario(@Path(value = "usuario", encoded = true) String : String) : Call<HeaderUsuario>

//    RUTAS DE FAVORITOS
    @GET("favorito/{usuario}/lista")
    fun listaFavorito(@Path(value = "usuario", encoded = true) String : String) : Call<HeaderFavorito>

    @Headers("content-type: application/json")
    @POST("favorito")
    fun storeFavorito(@Body request : FavoritoReq) : Call<HeaderFavorito>

    @DELETE("favorito/{favorito}/destroy")
    fun destroyFavorito(@Path(value = "favorito", encoded = true) String : String) : Call<HeaderFavorito>

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