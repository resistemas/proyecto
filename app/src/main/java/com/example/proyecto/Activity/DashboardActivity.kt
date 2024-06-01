package com.example.proyecto.Activity

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto.Adapter.MasVendidosAdapter
import com.example.proyecto.Domain.MasVendidos
import com.example.proyecto.Provider.MasVendidosProvider
import com.example.proyecto.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class DashboardActivity : AppCompatActivity() {
    private lateinit var recyclerMasVendidos : RecyclerView
    private lateinit var recyclerMasVendidosAdapter : RecyclerView
    private lateinit var recyclerRelacionados : RecyclerView
    private lateinit var mRequestQueue : RequestQueue
//    private lateinit var mStringRequest : String
    private lateinit var mStringRequest2 : String
    private lateinit var cargaMasVendidos : ProgressBar
    private lateinit var cargaRelacionados : ProgressBar

    private  lateinit var gson : Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loadingMasVendidos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        enviarPeticion()
    }

    private fun enviarPeticion() {
        recyclerMasVendidos.adapter = MasVendidosAdapter(MasVendidosProvider.masVendidoList)
//        mRequestQueue = Volley.newRequestQueue(this)
//        cargaMasVendidos.visibility = View.VISIBLE
//        var mStringRequest = StringRequest(Method.GET, "http://restapiayarte.test/producto/masvendido",
//            Response.Listener<String> { response ->
//                cargaMasVendidos.visibility = View.GONE
//                var items = gson.fromJson(response, MasVendidos::class.java)
//                recyclerMasVendidos.adapter = MasVendidosAdapter(listOf<MasVendidos>(items))
//            },
//            Response.ErrorListener { error ->
//                cargaMasVendidos.visibility = View.GONE
//            })
//        mRequestQueue.add(mStringRequest)
    }

    private fun initView() {
        recyclerMasVendidos = findViewById(R.id.reciclerMasVendidos)
        recyclerMasVendidos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerRelacionados =  findViewById(R.id.reciclerRelacionados)
        recyclerRelacionados.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        cargaMasVendidos = findViewById(R.id.loadingMasVendidos)
        cargaRelacionados = findViewById(R.id.loadingRelacionados)

    }
}