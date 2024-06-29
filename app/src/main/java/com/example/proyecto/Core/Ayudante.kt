package com.example.proyecto.Core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import com.example.proyecto.Activity.DashboardActivity
import com.example.proyecto.Activity.LoginActivity
import com.example.proyecto.Activity.PerfilActivity
import com.example.proyecto.Activity.ShopActivity
import com.example.proyecto.Model.Usuario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.dmoral.toasty.Toasty

class Ayudante(private val context: Context)  {
    private val PrefreciasGlobal : SharedPreferences =  context.getSharedPreferences("AyartePref", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun setPrefLogeuado(Logueado : Boolean){
        val editor = PrefreciasGlobal.edit()
        editor.putBoolean("Logueado", Logueado)
        editor.apply()
    }

    fun setPrefUsuario(Usuario : List<Usuario>){
        val data = gson.toJson(Usuario)
        val editor = PrefreciasGlobal.edit()
        editor.putString("usuario", data)
        editor.apply()
    }

    fun setPrefUsuarioToken(token : String){
        val editor = PrefreciasGlobal.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken() : String?{
        Variables.API_KEY = PrefreciasGlobal.getString("token","").toString()
        return PrefreciasGlobal.getString("token", "")
    }

   fun Logueado() : Boolean{
       return PrefreciasGlobal.getBoolean("Logueado", false)
   }

    fun usuarioLoqueado(typeToken: TypeToken<List<Usuario>>) : List<Usuario>{
        val datos = PrefreciasGlobal.getString("usuario",null)
        return if(datos != null){
            gson.fromJson(datos, typeToken.type)
        }else{
            emptyList()
        }
    }

    fun getIdUsuario() : Int? {
        val typeToken = object : TypeToken<List<Usuario>>() {}
        var usuario = usuarioLoqueado(typeToken)
        if(usuario.size > 0){
            return usuario.first().id
        }else{
            return 0
        }


    }

    fun introView(llave : String) : Boolean{
        return PrefreciasGlobal.contains(llave)
    }

    fun viewLogin(splash : SplashScreen, view : Activity ){
        if(!this.Logueado()){
            splash.setKeepOnScreenCondition{true}
            this.showInfo("Usted aun no tiene una Sesión Activa, Por favor Inice Sesión.")
            Handler(Looper.getMainLooper()).postDelayed({
                val intent : Intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                view.finish()
            }, 2500)
        }
    }

    fun viewShop(splash : SplashScreen, view : Activity ){
        if(this.Logueado()){
            splash.setKeepOnScreenCondition{true}
            Handler(Looper.getMainLooper()).postDelayed({
                val intent : Intent = Intent(context, ShopActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                view.finish()
            }, 2500)
        }
    }

    fun viewLoginProfile(splash : SplashScreen, view : Activity ){
        if(this.Logueado()){
            splash.setKeepOnScreenCondition{true}
            val intent : Intent = Intent(context, PerfilActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
            view.finish()
        }
    }

    fun viewDasboard(splash : SplashScreen, view : Activity ){
        splash.setKeepOnScreenCondition{true}
        this.showSuccess("Usted acaba de Cerrar Sesión.")
        Handler(Looper.getMainLooper()).postDelayed({
            val intent : Intent = Intent(context, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
            view.finish()
        }, 2500)

    }

    fun showSuccess(message : String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showError(message : String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showInfo(message : String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showWarning(message : String) {
        Toasty.warning(context, message, Toast.LENGTH_SHORT,true).show()
    }

    //    API WHATSAPP
    fun showWasa(message : String){
        val sendIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("http://api.whatsapp.com/send?phone=+936954810&text=$message")
        }
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(sendIntent)
        } else {
            showWarning("Usted no tiene instalado la aplicacion requerida.")
        }
    }
}