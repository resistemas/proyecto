package com.example.proyecto.ViewModel

import android.app.Activity
import android.util.Log
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Header.HeaderVenta
import com.example.proyecto.Model.Request.VentaReq
import com.example.proyecto.Model.Venta
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VentaViewModel(private  val Preferencias : Ayudante, private val view : Activity, private val splash : SplashScreen) : ViewModel() {
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    private var ventaLista = MutableLiveData<List<Venta>>()
    val ventasLista : LiveData<List<Venta>> = ventaLista

    fun getVentas(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.listadoVentas()
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            ventaLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            ventaLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun getVentasCliente(cliente : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.listadoVentaCliente(cliente)
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            ventaLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            ventaLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun getVentasArtesano(artesano : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.listadoVentaArtesano(artesano)
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            ventaLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            ventaLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun getVenta(venta : String){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.getVenta(venta)
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            ventaLista.value = res.data
                            Preferencias.showSuccess(res.message.toString())
                        }else{
                            ventaLista.value = listOf()
                            if (!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showWarning(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun storeVenta(artesanoId : String, clienteId : String, ciudad : String, direccion : String, productoId: String, cantidad : String, total : String, estado : String = "Iniciado"){
        val req = VentaReq(artesanoId, clienteId, ciudad, direccion, productoId, cantidad, total, estado)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.storeVenta(req)
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Preferencias.showSuccess(res.message.toString())
                            Preferencias.showWasa("Realise una compra con codigo: ${res.data.first().codigo} y Direccion de Envio: ${res.data.first().ciudad} - ${res.data.first().direccion}")
                            Preferencias.viewShop(splash, view)
                        }else{
                            if(!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showInfo(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }

    fun putVenta(id : String, estado : String){
        val req = VentaReq("", "", "", "", "", "", "", estado)
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.putVenta(id, req)
            response.enqueue(object : Callback<HeaderVenta> {
                override fun onResponse(
                    call: Call<HeaderVenta>,
                    response: Response<HeaderVenta>
                ) {
                    val res = response.body()
                    if(response.isSuccessful){
                        if(res?.status == true){
                            Preferencias.showSuccess(res.message.toString())
                            Preferencias.viewShop(splash, view)
                        }else{
                            if(!res?.message.toString().contains("El token a expirado")){
                                Preferencias.showInfo(res?.message.toString())
                            }else{
                                Preferencias.setPrefLogeuado(false)
                                Preferencias.viewLogin(splash, view)
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<HeaderVenta>, t: Throwable) {
                    Log.d("IRAMUS", "Error: ${t.message}")
                }

            })
        }
    }
}