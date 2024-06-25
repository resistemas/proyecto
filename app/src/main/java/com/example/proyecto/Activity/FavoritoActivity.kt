package com.example.proyecto.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adapter.FavoritoAdapter
import com.example.proyecto.Core.Authenticable
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Usuario
import com.example.proyecto.R
import com.example.proyecto.ViewModel.Factory.FavoritoFactory
import com.example.proyecto.ViewModel.Factory.LoginFactory
import com.example.proyecto.ViewModel.FavoritoViewModel
import com.example.proyecto.ViewModel.LoginViewModel
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritoActivity : MainActivity(), Authenticable {
    private lateinit var viewModel : FavoritoViewModel
    private lateinit var Preferencias : Ayudante
    private lateinit var adapterFavorito : FavoritoAdapter
    private lateinit var rvFavoritoLista : RecyclerView
    private lateinit var pbFavoritoLista : ProgressBar
    private lateinit var splash : SplashScreen
    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
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

        initView()
        initViewModel()
        initRecycler()

    }

    private fun initView(){
        rvFavoritoLista = findViewById(R.id.rcFavoritosLista)
        pbFavoritoLista = findViewById(R.id.pbFavoritosLista)
        pbFavoritoLista.visibility = View.VISIBLE
    }

    private fun initRecycler(){
        rvFavoritoLista.layoutManager = LinearLayoutManager(this)
        adapterFavorito = FavoritoAdapter(this, arrayListOf(), viewModel)
        rvFavoritoLista.adapter  = adapterFavorito
    }

    private fun initViewModel(){
        val factory = FavoritoFactory(Preferencias)
        viewModel = ViewModelProvider(this, factory)[FavoritoViewModel::class.java]

        viewModel.favoritosLista.observe(this){
            adapterFavorito.favoritoLista = it
            adapterFavorito.notifyDataSetChanged()
            pbFavoritoLista.visibility = View.GONE
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getFavorito(Preferencias.getIdUsuario().toString())
            }
        }
    }
}