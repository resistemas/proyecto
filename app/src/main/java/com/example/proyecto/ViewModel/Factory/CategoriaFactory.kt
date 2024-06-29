package com.example.proyecto.ViewModel.Factory

import android.app.Activity
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.ViewModel.CategroiasViewModel

class CategoriaFactory(private val preferencias : Ayudante, private val view : Activity, private val splash : SplashScreen) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(CategroiasViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CategroiasViewModel(preferencias, view, splash) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}