package com.example.proyecto.Activity

import com.example.proyecto.R




import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.Activity.RegistrationUtil
class LoginActivity : AppCompatActivity() {
    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        txtUsuario = findViewById(R.id.txtUsuario)
        txtPassword = findViewById(R.id.txtPassword)
        txtConfirmPassword = findViewById(R.id.txtPassword)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loadingMasVendidos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
        val confirmPassword = txtConfirmPassword.text.toString()

        if (!RegistrationUtil.validateRegistrationInput(usuario, password, confirmPassword)) {
            if (usuario.isEmpty()) {
                txtUsuario.error = "El campo de usuario no puede estar vacío"
            } else if (usuario in RegistrationUtil.existingUsers) {
                txtUsuario.error = "El nombre de usuario ya está tomado"
            } else if (password.isEmpty()) {
                txtPassword.error = "El campo de contraseña no puede estar vacío"
            } else if (password != confirmPassword) {
                txtConfirmPassword.error = "La contraseña y la confirmación no coinciden"
            } else if (password.count { it.isDigit() } < 2) {
                txtPassword.error = "La contraseña debe contener al menos 2 números"
            }
            return false
        }
        return true
    }
}
