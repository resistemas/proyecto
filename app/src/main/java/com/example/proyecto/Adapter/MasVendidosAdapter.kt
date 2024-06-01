package com.example.proyecto.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Domain.MasVendidos
import com.example.proyecto.R

class MasVendidosAdapter(private val masVendidosLista : List<MasVendidos>) : RecyclerView.Adapter<MasVendidosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasVendidosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MasVendidosViewHolder(layoutInflater.inflate(R.layout.imagen_detalle, parent, false))
    }

    override fun onBindViewHolder(holder: MasVendidosViewHolder, position: Int) {
        val item = masVendidosLista[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = masVendidosLista.size
}