package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
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
import com.example.proyecto.Adapter.VentaAdapter
import com.example.proyecto.Core.Authenticable
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Usuario
import com.example.proyecto.R
import com.example.proyecto.ViewModel.Factory.FavoritoFactory
import com.example.proyecto.ViewModel.Factory.VentaFactory
import com.example.proyecto.ViewModel.FavoritoViewModel
import com.example.proyecto.ViewModel.VentaViewModel
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopActivity : MainActivity(), Authenticable {
    private lateinit var Preferencias : Ayudante
    private lateinit var splash : SplashScreen

    private lateinit var adapterVenta : VentaAdapter
    private lateinit var rvVentaLista : RecyclerView
    private lateinit var pbVentaLista : ProgressBar

    private lateinit var viewModel : VentaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        Preferencias.viewLogin(splash, this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)
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
        rvVentaLista = findViewById(R.id.rcComprasLista)
        pbVentaLista = findViewById(R.id.pbComprasLista)
        pbVentaLista.visibility = View.VISIBLE
    }

    private fun initRecycler(){
        rvVentaLista.layoutManager = LinearLayoutManager(this)
        adapterVenta = VentaAdapter(this, arrayListOf(), onSubmit = { id, estado ->
            putVenta(id, estado)
        })
        rvVentaLista.adapter  = adapterVenta
    }

    private fun initViewModel(){
        val factory = VentaFactory(Preferencias, this, splash)
        viewModel = ViewModelProvider(this, factory)[VentaViewModel::class.java]

        viewModel.ventasLista.observe(this){
            adapterVenta.ventasLista = it
            adapterVenta.notifyDataSetChanged()
            pbVentaLista.visibility = View.GONE
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val typeToken = object : TypeToken<List<Usuario>>() {}
                val user = Preferencias.usuarioLoqueado(typeToken)
                if(user.size > 0){
                    if(user.first().roles?.rol.toString().equals("Cliente")){
                        viewModel.getVentasCliente(Preferencias.getIdUsuario().toString())
                    }

                    if(user.first().roles?.rol.toString().equals("Artesano")){
                        viewModel.getVentasArtesano(Preferencias.getIdUsuario().toString())
                    }
                }
            }
        }
    }

    private fun putVenta(id : String, estado : String){
        viewModel.putVenta(id, estado)
    }
}