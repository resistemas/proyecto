package com.example.proyecto.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Core.Variables
import com.example.proyecto.Model.Header.HeadVerificarAuth
import com.example.proyecto.Model.Header.HeaderLogin
import com.example.proyecto.Model.Login
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private  val Preferencias : Ayudante) : ViewModel() {
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    private val _statusAuth = MutableLiveData<Boolean>()
    val statusAuth: LiveData<Boolean>
        get() = _statusAuth
    fun vericarAuthRequest(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.verficarAuth()
            response.enqueue(object : Callback<HeadVerificarAuth>{
                override fun onResponse(
                    call: Call<HeadVerificarAuth>,
                    response: Response<HeadVerificarAuth>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Preferencias.setPrefLogeuado(true)
                            Preferencias.setPrefUsuario(res.data)
                        }else{
                            Preferencias.setPrefLogeuado(false)
                            Preferencias.setPrefUsuario(listOf())
                        }
                    }
                }

                override fun onFailure(call: Call<HeadVerificarAuth>, t: Throwable) {
                    Log.d("IRAMUS", "Login Error: ${t.message}")
                }

            })
        }
    }

    fun autenticacion(email : String, password : String){
        val req = Login(email, password)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.autenticacionAuth(req)
            response.enqueue(object : Callback<HeaderLogin>{
                override fun onResponse(
                    call: Call<HeaderLogin>,
                    response: Response<HeaderLogin>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        Log.d("IRAMUS", res.toString())
                        if(res?.status == true){
                            Preferencias.setPrefLogeuado(true)
                            Preferencias.setPrefUsuarioToken(res.token.toString())
                            Variables.API_KEY = res.token.toString()
                            Preferencias.showSuccess(res.message.toString())
                            _statusAuth.value = res.status
                        }else{
                            Preferencias.setPrefLogeuado(false)
                            Preferencias.setPrefUsuario(listOf())

                            if (res?.message.toString().contains("El Correo Electronico ingresado no existe", ignoreCase = true)){
                                Preferencias.showError(res?.message.toString())
                            }else{
                                Preferencias.showInfo(res?.message.toString())
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<HeaderLogin>, t: Throwable) {
                    Log.d("IRAMUS", "Login Error: ${t.message}")
                }

            })
        }
    }
}