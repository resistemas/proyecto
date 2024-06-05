package com.example.proyecto.Activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.proyecto.Adapter.MasVendidosAdapter
import com.example.proyecto.Adapter.RelacionadosAdpater
import com.example.proyecto.R
import com.example.proyecto.ViewModel.ProductosViewModel
import com.example.proyecto.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {
    private lateinit var viewModel : ProductosViewModel
    private lateinit var adapterMasVendidos : MasVendidosAdapter
    private lateinit var rvMasVendido : RecyclerView
    private lateinit var pgMasVendidos : ProgressBar

    private lateinit var adapterRelacionados : RelacionadosAdpater
    private lateinit var rvRelacionados : RecyclerView
    private lateinit var pgRelacionados : ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loadingMasVendidos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvMasVendido = findViewById(R.id.reciclerMasVendidos)
        pgMasVendidos = findViewById(R.id.loadingMasVendidos)
        pgMasVendidos.visibility = View.VISIBLE

        rvRelacionados = findViewById(R.id.reciclerRelacionados)
        pgRelacionados = findViewById(R.id.loadingRelacionados)
        pgRelacionados.visibility = View.VISIBLE

        initView()
    }


    private fun initView() {
        //viewModel
        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]

        setupRecyclerView()

        viewModel.masVendidosRequest()
        viewModel.relacionadosRequest()


        viewModel.masVendidosLista.observe(this){
            adapterMasVendidos.masVendidosLista = it
            adapterMasVendidos.notifyDataSetChanged()
            pgMasVendidos.visibility = View.GONE
        }

        viewModel.relacionadosLista.observe(this){
            adapterRelacionados.relacionadosLista = it
            adapterRelacionados.notifyDataSetChanged()
            pgRelacionados.visibility = View.GONE
        }


    }

    private fun setupRecyclerView(){

        rvMasVendido.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterMasVendidos = MasVendidosAdapter(this, arrayListOf())
        rvMasVendido.adapter  = adapterMasVendidos

        rvRelacionados.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterRelacionados = RelacionadosAdpater(this, arrayListOf())
        rvRelacionados.adapter  = adapterRelacionados

    }
}