package com.example.proyecto.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.ViewModel.FavoritoViewModel

class FavoritoFactory(private val preferencias : Ayudante) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(FavoritoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FavoritoViewModel(preferencias) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}