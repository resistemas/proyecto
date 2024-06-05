package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.R
import com.example.proyecto.ViewModel.ProductosViewModel
import com.google.android.material.imageview.ShapeableImageView

class DetalleActivity : AppCompatActivity() {
    private lateinit var viewModel : ProductosViewModel

    private lateinit var pgDetalle : ProgressBar

    private  lateinit var ivPosterGrande : ImageView
    private lateinit var ivPosterNormal : ShapeableImageView

    private lateinit var btnAtrasDetalle : ImageView
    private lateinit var btnFavoritoDetalle : ImageView

    private lateinit var tvTituloDetalle : TextView
    private lateinit var tvParrafoDetalle : TextView
    private lateinit var tvArtesanoDetalle : TextView

    private lateinit var cvRelacionadoDetalle : RecyclerView

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

        pgDetalle.visibility = View.VISIBLE

    }

    private fun initRequest(){
        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]

        val parametros = intent.extras
        val id = parametros?.getInt("id")

        viewModel.ProductoDetalle(id.toString())

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

            pgDetalle.visibility = View.GONE
        }
    }

    private fun btnActions(){
        btnAtrasDetalle.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}