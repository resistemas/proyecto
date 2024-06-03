package com.example.proyecto

import androidx.test.espresso.Espresso
import com.example.proyecto.Activity.DashboardActivity
import com.example.proyecto.Activity.IntroActivity
import com.example.proyecto.Activity.LoginActivity




import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppActivitiesTest {

    @Test
    fun testIntroActivity() {
        // Lanzar IntroActivity
        val introScenario = ActivityScenario.launch(IntroActivity::class.java)

        // Verificar que los elementos están visibles
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.btnIntro)).check(matches(isDisplayed()))

        // Simular clic en el botón de introducción
        onView(withId(R.id.btnIntro)).perform(click())

        // Verificar que DashboardActivity se abre
        onView(withId(R.id.reciclerMasVendidos)).check(matches(isDisplayed()))

        introScenario.close()
    }

    @Test
    fun testLoginActivity() {
        // Lanzar LoginActivity
        val loginScenario = ActivityScenario.launch(LoginActivity::class.java)

        // Verificar que los elementos están visibles
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
        onView(withId(R.id.txtUsuario)).check(matches(isDisplayed()))
        onView(withId(R.id.txtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btnIniciarSesion)).check(matches(isDisplayed()))

        // Simular entrada de texto en los campos de usuario y contraseña
        onView(withId(R.id.txtUsuario)).perform(typeText("testuser"))
        onView(withId(R.id.txtPassword)).perform(typeText("password"))
        Espresso.closeSoftKeyboard() // Cierra el teclado si está abierto

        // Simular clic en el botón de inicio de sesión
        onView(withId(R.id.btnIniciarSesion)).perform(click())

        // Verificar que la nueva actividad se abre (ajustar según implementación)
        // onView(withId(R.id.some_view_in_dashboard_activity))
        //         .check(matches(isDisplayed()))

        // Verificar texto de "Olvidé mi contraseña"
        onView(withId(R.id.txtRecuperar)).check(matches(isDisplayed()))
        onView(withId(R.id.txtRecuperar)).check(matches(withText(R.string.txtOlvide2)))

        // Simular clic en "Olvidé mi contraseña"
        onView(withId(R.id.txtRecuperar)).perform(click())

        loginScenario.close()
    }

    @Test
    fun testDashboardActivity() {
        // Lanzar DashboardActivity
        val dashboardScenario = ActivityScenario.launch(DashboardActivity::class.java)

        // Verificar que los elementos están visibles
        onView(withId(R.id.textView4)).check(matches(isDisplayed()))
        onView(withId(R.id.txtBuscarArtesania)).check(matches(isDisplayed()))
        onView(withId(R.id.reciclerMasVendidos)).check(matches(isDisplayed()))
        onView(withId(R.id.reciclerRelacionados)).check(matches(isDisplayed()))

        // Verificar que los ProgressBars no están visibles
        onView(withId(R.id.loadingMasVendidos)).check(matches(not(isDisplayed())))
        onView(withId(R.id.loadingRelacionados)).check(matches(not(isDisplayed())))

        dashboardScenario.close()
    }
}
