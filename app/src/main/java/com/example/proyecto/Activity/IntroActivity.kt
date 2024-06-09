package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.R
import com.example.proyecto.ViewModel.LoginViewModel

class IntroActivity : AppCompatActivity() {
    private lateinit var Preferencias : Ayudante
    private lateinit var viewModel: LoginViewModel
    private lateinit var btnIntro : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferencias = Ayudante(this)
        if(Preferencias.introView("Logueado")){
            val LoginInt = Intent(this, DashboardActivity::class.java)
            LoginInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(LoginInt)
            finish()
            return
        }
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.introContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btnIntro = findViewById(R.id.btnIntro)
        btnIntro.setOnClickListener{ startActivity(Intent(this, DashboardActivity::class.java)) }

    }

    private fun authVery(){
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.vericarAuthRequest()
    }
}