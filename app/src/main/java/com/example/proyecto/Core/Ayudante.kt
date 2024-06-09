package com.example.proyecto.Core

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
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

    fun introView(llave : String) : Boolean{
        return PrefreciasGlobal.contains(llave)
    }

    fun showSuccess(message : String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showError(message : String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showInfo(message : String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showWarning(message : String) {
        Toasty.warning(context, message, Toast.LENGTH_SHORT).show()
    }
}