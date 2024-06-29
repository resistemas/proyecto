package com.example.proyecto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Model.Categoria
import com.example.proyecto.Model.Venta
import com.example.proyecto.R

class CategoriaAdapter(
    var context: Context, var categoriasLista : List<Categoria>,
    private val onSubmit : (String) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCategoria = itemView.findViewById(R.id.txtCategoria) as TextView
        val btnCategoria = itemView.findViewById(R.id.btnShow) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_lista_categoria, parent, false)
        return CategoriaAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = categoriasLista.size

    override fun onBindViewHolder(holder: CategoriaAdapter.ViewHolder, position: Int) {
        val item = categoriasLista[position]

        holder.txtCategoria.text = "${item.categoria}"

        holder.btnCategoria.setOnClickListener {
            onSubmit.invoke(item.id.toString())
        }

    }
}