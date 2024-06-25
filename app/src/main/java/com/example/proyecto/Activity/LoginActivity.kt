package com.example.proyecto.Activity

import android.content.Intent
import com.example.proyecto.R




import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.Activity.RegistrationUtil
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Core.Validaciones
import com.example.proyecto.ViewModel.Factory.LoginFactory
import com.example.proyecto.ViewModel.LoginViewModel
import com.example.proyecto.ViewModel.ProductosViewModel
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var splash : SplashScreen
    private lateinit var Preferencias : Ayudante
    private lateinit var viewModel : LoginViewModel
    private lateinit var btnRecuperarCuenta : TextView
    private lateinit var btnFacebookFire  : ImageView
    private lateinit var btnGoogleFire : ImageView
    private lateinit var btnRegistro : TextView

    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIniciarSesion : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        splash = installSplashScreen()
        Preferencias = Ayudante(this)
        Preferencias.viewLoginProfile(splash, this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LoginContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initViewModel()
        btnActions()
    }

    private fun initView(){
        txtUsuario = findViewById(R.id.txtUsuario)
        txtPassword = findViewById(R.id.txtPassword)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnRecuperarCuenta = findViewById(R.id.btnRecuperarLogin)
        btnFacebookFire = findViewById(R.id.btnFacebookFire)
        btnGoogleFire = findViewById(R.id.btnGoogleFire)
        btnRegistro = findViewById(R.id.btnLoginRegistro)
    }

    private fun btnActions(){
        btnIniciarSesion.setOnClickListener { inicarSesion(txtUsuario.text.toString(), txtPassword.text.toString()) }
        btnRecuperarCuenta.setOnClickListener {  }
        btnFacebookFire.setOnClickListener {  }
        btnGoogleFire.setOnClickListener {  }
        btnRegistro.setOnClickListener { startActivity(Intent(this, RegistroActivity::class.java)) }
    }

    private fun initViewModel(){
        //factory
        val factory = LoginFactory(Preferencias)
        //viewModel
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun inicarSesion(email : String, password : String){
        val valid = Validaciones().login(email, password)
        if (valid != "Valido") {
            if(valid.contains("contraseña")){
                txtPassword.requestFocus()
                Preferencias.showInfo(valid)
            }else if(valid.contains("El Correo Electronico no es valido")){
                txtPassword.setText("")
                txtUsuario.requestFocus()
                Preferencias.showError(valid)
            }else{
                txtUsuario.requestFocus()
                Preferencias.showInfo(valid)
            }
            return
        }

        viewModel.statusAuth.observe(this){
            if(it){
                val intent : Intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        viewModel.autenticacion(email, password)
    }
}
