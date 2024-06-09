package com.example.proyecto.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Header.HeadVerificarAuth
import com.example.proyecto.Model.Usuario
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private  val Preferencias : Ayudante) : ViewModel() {
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    fun vericarAuthRequest(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.verficarAuth()
            response.enqueue(object : Callback<HeadVerificarAuth>{
                override fun onResponse(
                    call: Call<HeadVerificarAuth>,
                    response: Response<HeadVerificarAuth>
                ) {
                    val res = response.body()
                    if(res?.status == true){
                        Preferencias.setPrefLogeuado(true)
                        Preferencias.setPrefUsuario(res.data)
                    }else{
                        Preferencias.setPrefLogeuado(false)
                        Preferencias.setPrefUsuario(listOf())
                    }
                }

                override fun onFailure(call: Call<HeadVerificarAuth>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}