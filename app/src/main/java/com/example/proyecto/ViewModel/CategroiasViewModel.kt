package com.example.proyecto.ViewModel

import android.app.Activity
import android.util.Log
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Categoria
import com.example.proyecto.Model.Header.HeaderCategoria
import com.example.proyecto.Model.Header.HeaderVenta
import com.example.proyecto.Model.Venta
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategroiasViewModel(private  val Preferencias : Ayudante, private val view : Activity, private val splash : SplashScreen) : ViewModel() {
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    private var categoriaLista = MutableLiveData<List<Categoria>>()
    val categoriasLista : LiveData<List<Categoria>> = categoriaLista

    fun listaCategorias(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.categoriaLista()
            response.enqueue(object : Callback<HeaderCategoria> {
                override fun onResponse(
                    call: Call<HeaderCategoria>,
                    response: Response<HeaderCategoria>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            categoriaLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            categoriaLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderCategoria>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }
}