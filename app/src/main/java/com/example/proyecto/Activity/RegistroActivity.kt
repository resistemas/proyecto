package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Core.Validaciones
import com.example.proyecto.R
import com.example.proyecto.ViewModel.Factory.UsuarioFactory
import com.example.proyecto.ViewModel.UsuarioViewModel

class RegistroActivity : AppCompatActivity() {
    private lateinit var splash: SplashScreen
    private lateinit var Preferencias : Ayudante
    private lateinit var viewModel : UsuarioViewModel

    private lateinit var etNombres : EditText
    private lateinit var etCorreo : EditText
    private lateinit var etPassword : EditText
    private lateinit var etPasswordConf : EditText

    private lateinit var btnIniciar : TextView
    private lateinit var btnRegistro : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initViewModel()
        initButtons()
    }

    private fun initView(){
        btnIniciar = findViewById(R.id.btnLoginReg)
        btnRegistro = findViewById(R.id.btnRegistroReg)

        etNombres = findViewById(R.id.txtNombreReg)
        etCorreo = findViewById(R.id.txtCorreoReg)
        etPassword = findViewById(R.id.txtPasswordReg)
        etPasswordConf = findViewById(R.id.txtPasswordConfReg)
    }

    private fun initButtons(){
        btnIniciar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRegistro.setOnClickListener { registro() }
    }

    private fun initViewModel(){
        val fact = UsuarioFactory(Preferencias)
        viewModel = ViewModelProvider(this, fact)[UsuarioViewModel::class.java]
    }

    private fun registro(){
        val vw = viewModel
        val valid = Validaciones().registroUsuario(etNombres.text.toString().trim(), etCorreo.text.toString().trim(), etPassword.text.toString().trim(), etPasswordConf.text.toString().trim())
        if(valid == "Valido"){
        vw.storeUsuario(3, etNombres.text.toString().trim(), etCorreo.text.toString().trim(), "https://cdn.pixabay.com/photo/2017/07/18/23/23/user-2517433_1280.png", "Normal", etPassword.text.toString().trim(), "Activo")
        }else{
            Preferencias.showInfo(valid)
        }
    }
}