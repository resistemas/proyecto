package com.example.proyecto.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.R

class FavoritoActivity : MainActivity() {
    private lateinit var Preferencias : Ayudante
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        Preferencias = Ayudante(this)
        Preferencias.viewLogin(splash, this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}