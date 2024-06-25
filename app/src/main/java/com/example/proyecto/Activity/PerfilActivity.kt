package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Core.Authenticable
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Model.Usuario
import com.example.proyecto.R
import com.google.gson.reflect.TypeToken

class PerfilActivity : MainActivity(), Authenticable {
    private lateinit var splash : SplashScreen
    private lateinit var Preferencias : Ayudante

    private lateinit var btnEditar : ImageView
    private lateinit var btnSalir : ImageView

    private lateinit var ivPhotoPerfil : ImageView
    private lateinit var txtNombrePerfil : TextView
    private lateinit var txtRolPerfil : TextView
    private lateinit var txtCodigoPerfil : TextView
    private lateinit var txtCorreoPerfil : TextView
    private lateinit var txtEstadoPerfil : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initButtons()
        setPerfil()
    }

    private fun initView(){
        btnEditar = findViewById(R.id.btnEditPerfil)
        btnSalir = findViewById(R.id.btnSalirPerfil)
        ivPhotoPerfil = findViewById(R.id.ivPhotoPerfil)
        txtNombrePerfil = findViewById(R.id.txtNombrePerfil)
        txtRolPerfil = findViewById(R.id.txtRolPerfil)
        txtCodigoPerfil = findViewById(R.id.txtCodigoPerfil)
        txtCorreoPerfil = findViewById(R.id.txtCorreoPerfil)
        txtEstadoPerfil = findViewById(R.id.txtEstadoPerfil)
    }

    private fun initButtons(){
        btnEditar.setOnClickListener {  }

        btnSalir.setOnClickListener {
            Preferencias.setPrefLogeuado(false)
            Preferencias.setPrefUsuarioToken("Token.Nulled")
            Preferencias.viewDasboard(splash, this)
        }
    }

    private fun setPerfil(){
        val typeToken = object : TypeToken<List<Usuario>>() {}
        var usuario = Preferencias.usuarioLoqueado(typeToken)
        if(usuario.size > 0){

            Glide.with(this).load(usuario.first().photo)
                .override(120, 120)
                .transforms(RoundedCorners(80))
                .into(ivPhotoPerfil)

            txtNombrePerfil.text = usuario.first().nombresApellidos
            txtRolPerfil.text = usuario.first().roles?.rol
            txtCodigoPerfil.text = usuario.first().codigo
            txtCorreoPerfil.text = usuario.first().correoElectronico
            txtEstadoPerfil.text = usuario.first().estado

        }else{
            Preferencias.viewLogin(splash, this)
        }
    }
}