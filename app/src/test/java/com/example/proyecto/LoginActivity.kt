package com.example.proyecto



import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.R

class LoginActivity : AppCompatActivity() {
    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        txtUsuario = findViewById(R.id.txtUsuario)
        txtPassword = findViewById(R.id.txtPassword)

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

        if (usuario.isEmpty()) {
            txtUsuario.error = "El campo de usuario no puede estar vacío"
            return false
        }

        if (!isValidUser(usuario)) {
            txtUsuario.error = "El usuario debe contener al menos 8 caracteres y caracteres especiales"
            return false
        }

        if (password.isEmpty()) {
            txtPassword.error = "El campo de contraseña no puede estar vacío"
            return false
        }

        if (!isValidPassword(password)) {
            txtPassword.error = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúsculas, minúsculas, números y caracteres especiales"
            return false
        }

        return true
    }

    private fun isValidUser(user: String): Boolean {
        return user.length >= 8 && user.any { it.isDigit() } && user.any { !it.isLetterOrDigit() }
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isDigit() } &&
                password.any { !it.isLetterOrDigit() }
    }
}
