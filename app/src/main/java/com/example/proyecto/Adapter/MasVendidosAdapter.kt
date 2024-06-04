package com.example.proyecto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto.Model.Productos
import com.example.proyecto.R

class MasVendidosAdapter(
    val context : Context,
    var masVendidosLista : List<Productos>
) : RecyclerView.Adapter<MasVendidosAdapter.ViewHolder>() {
    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        val cvImagen = itemView.findViewById(R.id.imgDetalleImagen) as ImageView
        val tvTitulo = itemView.findViewById(R.id.txtTituloDetalleImagen) as TextView
        val tvScore = itemView.findViewById(R.id.txtScoreDetalleImagen) as TextView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imagen_detalle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val masVendido = masVendidosLista[position]

        Glide.with(context).load(masVendido.photo_video).apply(RequestOptions().override(120, 180))
            .into(holder.cvImagen)

        holder.tvTitulo.text = masVendido.producto
        holder.tvScore.text = "5"

        holder.cvImagen.setOnClickListener {
            showOverview(masVendido.descripcion, masVendido.producto)
        }
    }

    override fun getItemCount(): Int = masVendidosLista.size

    fun showOverview(descripcion : String, producto : String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(producto)
        builder.setMessage(descripcion)
        builder.show()
    }
}