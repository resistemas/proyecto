package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adapter.CategoriaAdapter
import com.example.proyecto.Adapter.VentaAdapter
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Usuario
import com.example.proyecto.R
import com.example.proyecto.ViewModel.CategroiasViewModel
import com.example.proyecto.ViewModel.Factory.CategoriaFactory
import com.example.proyecto.ViewModel.Factory.VentaFactory
import com.example.proyecto.ViewModel.VentaViewModel
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriasActivity : MainActivity() {
    private lateinit var Preferencias : Ayudante
    private lateinit var splash : SplashScreen

    private lateinit var adapterCategoria : CategoriaAdapter
    private lateinit var rvCategoriaLista : RecyclerView
    private lateinit var pbCategoriaLista : ProgressBar

    private lateinit var viewModel : CategroiasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categorias)
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
        rvCategoriaLista = findViewById(R.id.rcCategorias)
        pbCategoriaLista = findViewById(R.id.pbCategorias)
        pbCategoriaLista.visibility = View.VISIBLE
    }

    private fun initRecycler(){
        rvCategoriaLista.layoutManager = LinearLayoutManager(this)
        adapterCategoria = CategoriaAdapter(this, arrayListOf(), onSubmit = { id ->
            showProductos(id)
        })
        rvCategoriaLista.adapter  = adapterCategoria
    }

    private fun initViewModel(){
        val factory = CategoriaFactory(Preferencias, this, splash)
        viewModel = ViewModelProvider(this, factory)[CategroiasViewModel::class.java]

        viewModel.categoriasLista.observe(this){
            adapterCategoria.categoriasLista = it
            adapterCategoria.notifyDataSetChanged()
            pbCategoriaLista.visibility = View.GONE
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.listaCategorias()
            }
        }
    }

    private fun showProductos(id : String){
        if(id != ""){
            val intent = Intent(this, ProductosActivity::class.java)
            intent.putExtra("texto", id)
            intent.putExtra("categoria", true)
            startActivity(intent)
        }
    }
}