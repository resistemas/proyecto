package com.example.proyecto.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Domain.MasVendidos
import com.example.proyecto.R

class MasVendidosViewHolder(view : View) : RecyclerView.ViewHolder(view){

    var banner = view.findViewById<ImageView>(R.id.imgDetalleImagen)
    val titulo = view.findViewById<TextView>(R.id.txtTituloDetalleImagen)
    val score = view.findViewById<TextView>(R.id.txtScoreDetalleImagen)

    fun render(artesania : MasVendidos){
        titulo.text = artesania.producto.toString()
        score.text  = "5"
    }
}