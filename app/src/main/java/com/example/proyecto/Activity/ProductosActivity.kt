package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adapter.NuevosAdpater
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Productos
import com.example.proyecto.R
import com.example.proyecto.ViewModel.ProductosViewModel

class ProductosActivity : MainActivity() {
    private lateinit var Preferencias : Ayudante
    private lateinit var splash : SplashScreen
    private lateinit var viewModel : ProductosViewModel

    private lateinit var txtBuscar : EditText
    private lateinit var btnBuscar : ImageView

    private lateinit var cvProductos : RecyclerView
    private lateinit var adapterProductos : NuevosAdpater
    private lateinit var pbProductos : ProgressBar

    private lateinit var texto : String
    private lateinit var estadotype : String

    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initViewModel()
        initRecycler()
        initObserver()
        initButtons()
    }

    private fun initView(){
        val parametros = intent.extras
        texto = parametros?.getString("texto").toString()
        estadotype = parametros?.getBoolean("categoria").toString()

        txtBuscar = findViewById(R.id.txtBuscarPro)
        btnBuscar = findViewById(R.id.btnSearchPro)
        cvProductos = findViewById(R.id.cvProductos)
        pbProductos = findViewById(R.id.pbProductos)
        pbProductos.visibility = View.VISIBLE

        if(estadotype == "false"){
            txtBuscar.setText(texto)
        }
    }

    private fun initRecycler(){
        cvProductos.layoutManager = GridLayoutManager(this, 3)
        adapterProductos = NuevosAdpater(this, arrayListOf())
        cvProductos.adapter  = adapterProductos
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]
    }

    private fun initObserver(){
        viewModel.productosLista.observe(this){
            adapterProductos.nuevosLista = it
            adapterProductos.notifyDataSetChanged()
            pbProductos.visibility = View.GONE
        }

        viewModel.toastMessage.observe(this){
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        if(estadotype == "true"){
            viewModel.buscarCategoriaRequest(texto)
        }else{
            viewModel.buscarRequest(texto)
        }

    }

    private fun initButtons(){
        btnBuscar.setOnClickListener {
            if(txtBuscar.text.toString().trim() != ""){
                val intent = Intent(this, ProductosActivity::class.java)
                intent.putExtra("texto", txtBuscar.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}