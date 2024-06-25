package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentDialog
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Activity.Components.ComprarModal
import com.example.proyecto.Adapter.NuevosAdpater
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Request.FavoritoReq
import com.example.proyecto.R
import com.example.proyecto.ViewModel.Factory.FavoritoFactory
import com.example.proyecto.ViewModel.FavoritoViewModel
import com.example.proyecto.ViewModel.ProductosViewModel
import com.google.android.material.imageview.ShapeableImageView

class DetalleActivity : MainActivity() {
    private lateinit var Preferencias : Ayudante
    private lateinit var splash : SplashScreen
    private lateinit var viewModel : ProductosViewModel
    private lateinit var viewModelFavorito : FavoritoViewModel

    private lateinit var pgDetalle : ProgressBar

    private lateinit var svContainer : ScrollView
    private  lateinit var ivPosterGrande : ImageView
    private lateinit var ivPosterNormal : ShapeableImageView

    private lateinit var btnAtrasDetalle : ImageView
    private lateinit var btnFavoritoDetalle : ImageView
    private lateinit var btnShopDetalle : ImageView

    private lateinit var tvTituloDetalle : TextView
    private lateinit var tvParrafoDetalle : TextView
    private lateinit var tvArtesanoDetalle : TextView

    private lateinit var cvRelacionadoDetalle : RecyclerView
    private lateinit var adapterRelacionados : NuevosAdpater

    var producto : String = "0"
    var cliente : String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
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
        initViewModels()
        btnActions()
    }

    private fun initItems(){
        pgDetalle = findViewById(R.id.progresoDetalle)
        ivPosterGrande = findViewById(R.id.posterGrande)
        ivPosterNormal = findViewById(R.id.posterNormales)
        btnAtrasDetalle = findViewById(R.id.btnDetalleAtras)
        btnFavoritoDetalle = findViewById(R.id.btnDetalleFavorite)
        btnShopDetalle = findViewById(R.id.btnShopDetalle)
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
            producto = it.first().id.toString()
            tvParrafoDetalle.text = it.first().descripcion
            tvArtesanoDetalle.text = it.first().vendedor!!.nombresApellidos
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

        viewModel.productosLista.observe(this){
            btnFavoritoDetalle.setOnClickListener {
                if(!Preferencias.Logueado() || Preferencias.getIdUsuario() == 0){
                    Preferencias.viewLogin(splash, this)
                }else{
                    val parametros = intent.extras
                    viewModelFavorito.storeFavorito(Preferencias.getIdUsuario().toString(),parametros?.getInt("id").toString(), "Activo")
                }
            }
        }


        btnShopDetalle.setOnClickListener {
            val parametros = intent.extras
            val id = parametros?.getInt("id")
            val categoria = parametros?.getInt("categoria")


            if(!Preferencias.Logueado() || Preferencias.getIdUsuario() == 0){
                Preferencias.viewLogin(splash, this)
            }else {
                ComprarModal(
                    onSubmit = { ciudad, direccion, cantidad, total ->
                        Preferencias.showInfo(ciudad.toString())
                    }
                ).show(supportFragmentManager, "ModalCompra")

//                val intent: Intent = Intent(this, ShopActivity::class.java)
//                intent.putExtra("id", id)
//                intent.putExtra("categoria", categoria)
//                startActivity(intent)
//                finish()
            }
        }


    }

    private fun initViewModels(){
        val factory = FavoritoFactory(Preferencias)
        viewModelFavorito = ViewModelProvider(this, factory)[FavoritoViewModel::class.java]
    }
}