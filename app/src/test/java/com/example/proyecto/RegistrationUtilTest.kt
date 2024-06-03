package com.example.proyecto

import com.example.proyecto.Activity.RegistrationUtil
import org.junit.Assert.*
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `nombre de usuario vacío devuelve falso`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "",
            password = "123",
            confirmedPassword = "123"
        )
        assertFalse(result)
    }

    @Test
    fun `nombre de usuario válido y contraseña repetida correctamente devuelven verdadero`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "kira",
            password = "123Moda",
            confirmedPassword = "123Moda"
        )
        assertTrue(result)
    }

    @Test
    fun `nombre de usuario ya existe devuelve falso`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Leo",
            password = "1900",
            confirmedPassword = "1900"
        )
        assertFalse(result) // Esto debe ser falso, ya que el usuario existe
    }

    @Test
    fun `contraseña con menos de 2 números devuelve falso`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Lucas",
            password = "abcdf5",
            confirmedPassword = "abcdf5"
        )
        assertFalse(result)
    }

    @Test
    fun `contraseña vacía devuelve falso`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Lucas",
            password = "",
            confirmedPassword = ""
        )
        assertFalse(result)
    }

    @Test
    fun `contraseña confirmada incorrectamente devuelve falso`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Ana",
            password = "123g23",
            confirmedPassword = "dfghtr"
        )
        assertFalse(result)
    }
}
