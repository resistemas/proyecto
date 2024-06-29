package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Authenticable
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.R
import com.example.proyecto.ViewModel.Factory.LoginFactory
import com.example.proyecto.ViewModel.LoginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

open class MainActivity : AppCompatActivity() {
    private lateinit var viewModelLogin : LoginViewModel
    private lateinit var Preferencias : Ayudante
    private lateinit var splash : SplashScreen

    private lateinit var btnDashboard : ImageView
    private lateinit var btnFavorito : ImageView
    private lateinit var btnShop : ImageView
    private lateinit var btnLogin : ImageView
    private lateinit var btnCategorias : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        Preferencias.getToken()
        val ftLogin = LoginFactory(Preferencias)
        viewModelLogin = ViewModelProvider(this, ftLogin).get(LoginViewModel::class.java)
        viewModelLogin.vericarAuthRequest()
    }
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initView()
        btnActions()
    }

    private fun initView(){
        btnDashboard = findViewById(R.id.btnDashboard)
        btnFavorito = findViewById(R.id.btnFavorito)
        btnShop = findViewById(R.id.btnShop)
        btnLogin = findViewById(R.id.btnLogin)
        btnCategorias = findViewById(R.id.btnCategoria)
    }

    private fun btnActions(){
        btnDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        btnFavorito.setOnClickListener {
            startActivity(Intent(this, FavoritoActivity::class.java))
            finish()
        }

        btnShop.setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
            finish()
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnCategorias.setOnClickListener {
            startActivity(Intent(this, CategoriasActivity::class.java))
            finish()
        }
    }

}