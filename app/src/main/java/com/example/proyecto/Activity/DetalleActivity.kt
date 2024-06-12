package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Adapter.NuevosAdpater
import com.example.proyecto.R
import com.example.proyecto.ViewModel.ProductosViewModel
import com.google.android.material.imageview.ShapeableImageView

class DetalleActivity : MainActivity() {
    private lateinit var viewModel : ProductosViewModel

    private lateinit var pgDetalle : ProgressBar

    private lateinit var svContainer : ScrollView
    private  lateinit var ivPosterGrande : ImageView
    private lateinit var ivPosterNormal : ShapeableImageView

    private lateinit var btnAtrasDetalle : ImageView
    private lateinit var btnFavoritoDetalle : ImageView

    private lateinit var tvTituloDetalle : TextView
    private lateinit var tvParrafoDetalle : TextView
    private lateinit var tvArtesanoDetalle : TextView

    private lateinit var cvRelacionadoDetalle : RecyclerView
    private lateinit var adapterRelacionados : NuevosAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initItems()
        setupRc()
        initRequest()
        btnActions()
    }

    private fun initItems(){
        pgDetalle = findViewById(R.id.progresoDetalle)
        ivPosterGrande = findViewById(R.id.posterGrande)
        ivPosterNormal = findViewById(R.id.posterNormales)
        btnAtrasDetalle = findViewById(R.id.btnDetalleAtras)
        btnFavoritoDetalle = findViewById(R.id.btnDetalleFavorite)
        tvTituloDetalle = findViewById(R.id.textTituloDetalle)
        tvParrafoDetalle = findViewById(R.id.txtDetalleParrafo)
        tvArtesanoDetalle = findViewById(R.id.txtDetalleArtesano)
        cvRelacionadoDetalle = findViewById(R.id.reciclerRelacionadoDetalle)
        svContainer = findViewById(R.id.scrollView3)

        pgDetalle.visibility = View.VISIBLE
        svContainer.visibility = View.GONE

    }

    private fun initRequest(){
        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]

        val parametros = intent.extras
        val id = parametros?.getInt("id")
        val categoria = parametros?.getInt("categoria")

        viewModel.ProductoDetalle(id.toString())
        viewModel.ProductoDetalleRelacionado(categoria.toString())

        viewModel.productosLista.observe(this){
            tvTituloDetalle.text = it.first().producto
            tvParrafoDetalle.text = it.first().descripcion
            tvArtesanoDetalle.text = it.first().vendedor.nombresApellidos
            Glide.with(this).load(it.first().photo_video)
                .override(120, 180)
                .transforms(RoundedCorners(15))
                .into(ivPosterGrande)

            Glide.with(this).load(it.first().photo_video)
                .override(210, 300)
                .transforms(RoundedCorners(15))
                .into(ivPosterNormal)

            svContainer.visibility = View.VISIBLE
            pgDetalle.visibility = View.GONE


        }

        viewModel.nuevosLista.observe(this){
            adapterRelacionados.nuevosLista = it
            adapterRelacionados.notifyDataSetChanged()
        }

        viewModel.toastMessage.observe(this){
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRc(){
        cvRelacionadoDetalle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterRelacionados = NuevosAdpater(this, arrayListOf())
        cvRelacionadoDetalle.adapter  = adapterRelacionados
    }

    private fun btnActions(){
        btnAtrasDetalle.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        btnFavoritoDetalle.setOnClickListener {
            startActivity(Intent(this, FavoritoActivity::class.java))
            finish()
        }
    }
}