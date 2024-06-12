package com.example.proyecto.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.ViewModel.LoginViewModel

class LoginFactory(private val preferencias : Ayudante) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(preferencias) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}