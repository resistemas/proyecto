package com.example.proyecto.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Model.Header.HeadMasVendidos
import com.example.proyecto.Model.Header.HeadProductos
import com.example.proyecto.Model.Productos
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductosViewModel : ViewModel() {

    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    private var masVendidoLista = MutableLiveData<List<Productos>>()
    val masVendidosLista : LiveData<List<Productos>> = masVendidoLista

    private var nuevoLista = MutableLiveData<List<Productos>>()
    val nuevosLista : LiveData<List<Productos>> = nuevoLista

    private var productoLista = MutableLiveData<List<Productos>>()
    val productosLista : LiveData<List<Productos>> = productoLista

//    Seccion para el toast
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun masVendidosRequest(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.masVendidos()
            response.enqueue(object : Callback<HeadMasVendidos>{
                override fun onResponse(call: Call<HeadMasVendidos>, response: Response<HeadMasVendidos>) {
                    if(response.isSuccessful){
                        val res = response.body()
                        if(res?.status == true){
                            val productosMasVendidos = res.data
                            val listaProductos = mutableListOf<Productos>()
                            for (venta in productosMasVendidos) {
                                venta.producto?.let { producto ->
                                    listaProductos.add(producto)
                                }
                            }
                            masVendidoLista.value = listaProductos

                        }else{
                            _toastMessage.value = res?.message
                        }
                    }
                }

                override fun onFailure(call: Call<HeadMasVendidos>, t: Throwable) {
                    _toastMessage.value = t?.message
                }

            })
        }
    }

    fun nuevosRequest(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.nuevos()
                response.enqueue(object : Callback<HeadProductos>{
                override fun onResponse(call: Call<HeadProductos>, response: Response<HeadProductos>) {
                    if(response.isSuccessful){
                        val res = response.body()
                        if(res?.status == true){
                            val productosNuevos = res.data
                            nuevoLista.value = productosNuevos
                        }else{
                            _toastMessage.value = res?.message
                        }
                    }
                }

                override fun onFailure(call: Call<HeadProductos>, t: Throwable) {
                    _toastMessage.value = t?.message
                }

            })
        }
    }

    fun ProductoDetalle(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.productoDetalle(id)
            response.enqueue(object : Callback<HeadProductos>{
                override fun onResponse(
                    call: Call<HeadProductos>,
                    response: Response<HeadProductos>
                ) {
                    if(response.isSuccessful){
                        val res = response.body()
                        if(res?.status == true){
                            val productosNuevos = res.data
                            productoLista.value = productosNuevos
                        }else{
                            _toastMessage.value = res?.message
                        }
                    }
                }

                override fun onFailure(call: Call<HeadProductos>, t: Throwable) {
                    _toastMessage.value = t?.message
                }

            })
        }
    }

    fun ProductoDetalleRelacionado(categoria : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.productoDetalleRelacionado(categoria)
            response.enqueue(object : Callback<HeadProductos>{
                override fun onResponse(
                    call: Call<HeadProductos>,
                    response: Response<HeadProductos>
                ) {
                    if(response.isSuccessful){
                        val res = response.body()
                        if(res?.status == true){
                            val productosNuevos = res.data
                            nuevoLista.value = productosNuevos
                        }else{
                            _toastMessage.value = res?.message
                        }
                    }
                }

                override fun onFailure(call: Call<HeadProductos>, t: Throwable) {
                    _toastMessage.value = t?.message
                }

            })
        }
    }
}