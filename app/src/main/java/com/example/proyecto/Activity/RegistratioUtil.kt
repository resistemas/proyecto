package com.example.proyecto.Activity



object RegistrationUtil {

    val existingUsers = listOf("Leo", "Kira", "Ana")

    /**
     * La entrada no es válida si...
     * ...el nombre de usuario/password está vacío
     * ...el nombre de usuario ya está tomado
     * ...la contraseña y la confirmación no coinciden
     * ...la contraseña contiene menos de 2 números
     */
    fun validateRegistrationInput(
        username: String,
        password: String
    ): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            return false
        }
        if (username in existingUsers) {
            return false
        }
        if (password.count { it.isDigit() } < 2) {
            return false
        }
        return true
    }
}
