package com.example.proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.R

open class MainActivity : AppCompatActivity() {
    private lateinit var btnDashboard : ImageView
    private lateinit var btnFavorito : ImageView
    private lateinit var btnShop : ImageView
    private lateinit var btnLogin : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initView()
        btnActions()
    }

    private fun initView(){
        btnDashboard = findViewById(R.id.btnDashboard)
        btnFavorito = findViewById(R.id.btnFavorito)
        btnShop = findViewById(R.id.btnShop)
        btnLogin = findViewById(R.id.btnLogin)
    }

    private fun btnActions(){
        btnDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        btnFavorito.setOnClickListener {
            startActivity(Intent(this, FavoritoActivity::class.java))
        }

        btnShop.setOnClickListener {

        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}