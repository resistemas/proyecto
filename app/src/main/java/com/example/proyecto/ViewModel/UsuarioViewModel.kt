package com.example.proyecto.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Favorito
import com.example.proyecto.Model.Header.HeaderFavorito
import com.example.proyecto.Model.Header.HeaderUsuario
import com.example.proyecto.Model.Request.FavoritoReq
import com.example.proyecto.Model.Request.UsuarioReq
import com.example.proyecto.Model.Usuario
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioViewModel(private  val Preferencias : Ayudante) : ViewModel(){
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    private var usuarioLista = MutableLiveData<List<Usuario>>()
    val usuariosLista : LiveData<List<Usuario>> = usuarioLista

    fun getUsuarios(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.listadoUsuarios()
            response.enqueue(object : Callback<HeaderUsuario> {
                override fun onResponse(
                    call: Call<HeaderUsuario>,
                    response: Response<HeaderUsuario>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            usuarioLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            usuarioLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderUsuario>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun getUsuario(usuario : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.getUsuario(usuario)
            response.enqueue(object : Callback<HeaderUsuario> {
                override fun onResponse(
                    call: Call<HeaderUsuario>,
                    response: Response<HeaderUsuario>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            usuarioLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            usuarioLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderUsuario>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun storeUsuario(rol : Int, nombres : String, correo : String, photo : String, usuario: String, password : String, estado : String = "Activo"){
        val req = UsuarioReq(rol, nombres, correo, photo, usuario, password, estado)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.storeUsuario(req)
            response.enqueue(object : Callback<HeaderUsuario> {
                override fun onResponse(
                    call: Call<HeaderUsuario>,
                    response: Response<HeaderUsuario>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Log.d("REGISTRO", res.message.toString())
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            Log.d("REGISTRO", res?.message.toString())
                            if(!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showInfo(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderUsuario>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun putUsuario(id : String, rol : Int, nombres : String, correo : String, photo : String, usuario: String, password : String, estado : String = "Activo"){
        val req = UsuarioReq(rol, nombres, correo, photo, usuario, password, estado)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.putUsuario(id, req)
            response.enqueue(object : Callback<HeaderUsuario> {
                override fun onResponse(
                    call: Call<HeaderUsuario>,
                    response: Response<HeaderUsuario>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Log.d("MENSAJE-STORE-FAVORITO", res.message.toString())
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            Log.d("MENSAJE-STORE-FAVORITO", res?.message.toString())
                            if(!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showInfo(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<HeaderUsuario>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun deleteUsuario(usuario : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.destroyUsuario(usuario)
            response.enqueue(object : Callback<HeaderUsuario> {
                override fun onResponse(
                    call: Call<HeaderUsuario>,
                    response: Response<HeaderUsuario>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            Preferencias.showInfo(res?.message.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderUsuario>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }
}