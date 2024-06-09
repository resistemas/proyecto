package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adapter.MasVendidosAdapter
import com.example.proyecto.Adapter.NuevosAdpater
import com.example.proyecto.R
import com.example.proyecto.ViewModel.ProductosViewModel


class DashboardActivity : AppCompatActivity() {
    private lateinit var viewModel : ProductosViewModel
    private lateinit var adapterMasVendidos : MasVendidosAdapter
    private lateinit var rvMasVendido : RecyclerView
    private lateinit var pgMasVendidos : ProgressBar

    private lateinit var adapterRelacionados : NuevosAdpater
    private lateinit var rvRelacionados : RecyclerView
    private lateinit var pgRelacionados : ProgressBar

    private lateinit var btnDashboard : ImageView
    private lateinit var btnFavorito : ImageView
    private lateinit var btnShop : ImageView
    private lateinit var btnLogin : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loadingMasVendidos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnDashboard = findViewById(R.id.btnDashboard)
        btnFavorito = findViewById(R.id.btnFavorito)
        btnShop = findViewById(R.id.btnShop)
        btnLogin = findViewById(R.id.btnLogin)

        rvMasVendido = findViewById(R.id.reciclerMasVendidos)
        pgMasVendidos = findViewById(R.id.loadingMasVendidos)
        pgMasVendidos.visibility = View.VISIBLE

        rvRelacionados = findViewById(R.id.reciclerRelacionados)
        pgRelacionados = findViewById(R.id.loadingRelacionados)
        pgRelacionados.visibility = View.VISIBLE

        initView()
        btnActions()
    }


    private fun initView() {
        //viewModel
        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]

        setupRecyclerView()

        viewModel.masVendidosRequest()
        viewModel.nuevosRequest()


        viewModel.masVendidosLista.observe(this){
            adapterMasVendidos.masVendidosLista = it
            adapterMasVendidos.notifyDataSetChanged()
            pgMasVendidos.visibility = View.GONE
        }

        viewModel.nuevosLista.observe(this){
            adapterRelacionados.nuevosLista = it
            adapterRelacionados.notifyDataSetChanged()
            pgRelacionados.visibility = View.GONE
        }

        viewModel.toastMessage.observe(this){
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun setupRecyclerView(){

        rvMasVendido.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterMasVendidos = MasVendidosAdapter(this, arrayListOf())
        rvMasVendido.adapter  = adapterMasVendidos

        rvRelacionados.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterRelacionados = NuevosAdpater(this, arrayListOf())
        rvRelacionados.adapter  = adapterRelacionados

    }

    private fun btnActions(){
        btnDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        btnFavorito.setOnClickListener {
//            startActivity(Intent(this, ))
        }

        btnShop.setOnClickListener {

        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}