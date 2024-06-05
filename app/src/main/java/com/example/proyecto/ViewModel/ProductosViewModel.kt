package com.example.proyecto.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Model.MasVendidos
import com.example.proyecto.Model.Productos
import com.example.proyecto.Model.Relacionados
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductosViewModel : ViewModel() {

    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    private var masVendidoLista = MutableLiveData<List<MasVendidos>>()
    val masVendidosLista : LiveData<List<MasVendidos>> = masVendidoLista

    private var relacionadoLista = MutableLiveData<List<Relacionados>>()
    val relacionadosLista : LiveData<List<Relacionados>> = relacionadoLista

    private var productoLista = MutableLiveData<List<Productos>>()
    val productosLista : LiveData<List<Productos>> = productoLista

    fun masVendidosRequest(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.masVendidos()
                withContext(Dispatchers.Main){
                    masVendidoLista.value = response.body()!!.data
                }
        }
    }

    fun relacionadosRequest(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.relacionados()
            withContext(Dispatchers.Main){
                relacionadoLista.value = response.body()!!.data
            }
        }
    }

    fun ProductoDetalle(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.productoDetalle(id)
            withContext(Dispatchers.Main){
                productoLista.value = response.body()!!.data
            }
        }
    }

    fun ProductoDetalleRelacionado(categoria : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.productoDetalleRelacionado(categoria)
            withContext(Dispatchers.Main){
                relacionadoLista.value = response.body()!!.data
            }
        }
    }
}