package com.example.proyecto.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.ViewModel.UsuarioViewModel

class UsuarioFactory(private val preferencias : Ayudante) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(UsuarioViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(preferencias) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}