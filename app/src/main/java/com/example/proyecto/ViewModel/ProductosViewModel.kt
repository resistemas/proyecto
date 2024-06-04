package com.example.proyecto.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Model.Productos
import com.example.proyecto.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductosViewModel : ViewModel() {

    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    private var productoLista = MutableLiveData<List<Productos>>()
    val productosLista : LiveData<List<Productos>> = productoLista

    fun masVendidosRequest(){
        viewModelScope.launch(dispatcherIO){
            val response = RetrofitCliente.webService.masVendidos()
                withContext(Dispatchers.Main){
                    productoLista.value = response.body()!!.data
                }
        }
    }

    fun relacionadosRequest(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitCliente.webService.relacionados()
            withContext(Dispatchers.Main){
                productoLista.value = response.body()!!.data.sortedBy { (it.categoria_id).toInt() }
            }
        }
    }
}