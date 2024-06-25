package com.example.proyecto.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Favorito
import com.example.proyecto.Model.Header.HeaderFavorito
import com.example.proyecto.Model.Request.FavoritoReq
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritoViewModel(private  val Preferencias : Ayudante) : ViewModel() {
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    private var favoritoLista = MutableLiveData<List<Favorito>>()
    val favoritosLista : LiveData<List<Favorito>> = favoritoLista

    fun getFavorito(usuario : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.listaFavorito(usuario)
            response.enqueue(object : Callback<HeaderFavorito>{
                override fun onResponse(
                    call: Call<HeaderFavorito>,
                    response: Response<HeaderFavorito>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            favoritoLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            favoritoLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderFavorito>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun storeFavorito(cliente : String, producto : String, estado : String = "Activo"){
        val req = FavoritoReq(cliente, producto, estado)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.storeFavorito(req)
            response.enqueue(object : Callback<HeaderFavorito>{
                override fun onResponse(
                    call: Call<HeaderFavorito>,
                    response: Response<HeaderFavorito>
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

                override fun onFailure(call: Call<HeaderFavorito>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun deleteFavorito(favorito : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.destroyFavorito(favorito)
            response.enqueue(object : Callback<HeaderFavorito>{
                override fun onResponse(
                    call: Call<HeaderFavorito>,
                    response: Response<HeaderFavorito>
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

                override fun onFailure(call: Call<HeaderFavorito>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }
}