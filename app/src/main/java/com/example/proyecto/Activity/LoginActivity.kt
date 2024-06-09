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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.Activity.RegistrationUtil
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
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


        txtUsuario = findViewById(R.id.txtUsuario)
        txtPassword = findViewById(R.id.txtPassword)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LoginContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        btnActions()

        findViewById<View>(R.id.btnIniciarSesion).setOnClickListener {
            if (validateInputs()) {
                // Proceed with login
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val usuario = txtUsuario.text.toString()
        val password = txtPassword.text.toString()

        if (!RegistrationUtil.validateRegistrationInput(usuario, password)) {
            if (usuario.isEmpty()) {
                txtUsuario.error = "El campo de usuario no puede estar vacío"
            } else if (usuario in RegistrationUtil.existingUsers) {
                txtUsuario.error = "El nombre de usuario ya está tomado"
            } else if (password.isEmpty()) {
                txtPassword.error = "El campo de contraseña no puede estar vacío"
            }  else if (password.count { it.isDigit() } < 2) {
                txtPassword.error = "La contraseña debe contener al menos 2 números"
            }
            return false
        }
        return true
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
        btnIniciarSesion.setOnClickListener {  }
        btnRecuperarCuenta.setOnClickListener {  }
        btnFacebookFire.setOnClickListener {  }
        btnGoogleFire.setOnClickListener {  }
        btnRegistro.setOnClickListener { startActivity(Intent(this, RegistroActivity::class.java)) }
    }

    private fun inicarSesion(){

    }
}
