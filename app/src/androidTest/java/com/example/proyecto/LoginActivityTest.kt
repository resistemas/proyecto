package com.example.proyecto

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import com.example.proyecto.Activity.LoginActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    //val activityRule = ActivityTestRule(LoginActivity::class.java)
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun testViewsDisplayed() {
        // Verifica que los elementos de la UI están visibles
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
        onView(withId(R.id.txtUsuario)).check(matches(isDisplayed()))
        onView(withId(R.id.txtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btnIniciarSesion)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginButtonClick() {
        // Simula la entrada de texto en los campos de usuario y contraseña
        onView(withId(R.id.txtUsuario)).perform(typeText("Aldo1234"))
        onView(withId(R.id.txtPassword)).perform(typeText("Assd125*/53"))
        Espresso.closeSoftKeyboard() // Cierra el teclado si está abierto


        // Simula un clic en el botón de inicio de sesión
        onView(withId(R.id.btnIniciarSesion)).perform(click())

        // Verifica que la nueva actividad se abre (esto puede necesitar ajuste dependiendo de tu implementación)
        // Por ejemplo, si la nueva actividad tiene una vista con un ID específico
        // onView(withId(R.id.some_view_in_dashboard_activity))
        //         .check(matches(isDisplayed()))
    }

    @Test
    fun testForgotPasswordClick() {
        // Verifica que el texto de "Olvidé mi contraseña" está visible
//        onView(withId(R.id.txtRecuperar)).check(matches(isDisplayed()))
//        onView(withId(R.id.txtRecuperar)).check(matches(withText(R.string.txtOlvide2)))

        // Simula un clic en el texto de "Olvidé mi contraseña"
//        onView(withId(R.id.txtRecuperar)).perform(click())

        // Verifica que se muestra un mensaje o se realiza una acción específica
        // Esto puede necesitar ajuste dependiendo de tu implementación
        // Ejemplo:
        // onView(withText(R.string.password_recovery_message))
        //         .check(matches(isDisplayed()))
    }
}
