package com.example.proyecto.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Activity.DetalleActivity
import com.example.proyecto.Model.MasVendidos
import com.example.proyecto.R

class MasVendidosAdapter(
    val context : Context,
    var masVendidosLista : List<MasVendidos>
) : RecyclerView.Adapter<MasVendidosAdapter.ViewHolder>() {
    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        val clMasVendido = itemView.findViewById(R.id.clDetalleMasVendido) as ConstraintLayout
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

        Glide.with(context).load(masVendido.photo_video)
            .override(120, 180)
            .transforms(RoundedCorners(15))
            .into(holder.cvImagen)

        holder.cvImagen.setBackgroundResource(R.drawable.img_rouended)

        holder.tvTitulo.text = masVendido.producto
        holder.tvScore.text = masVendido.codigo

        holder.clMasVendido.setOnClickListener {
            val intent = Intent(context, DetalleActivity::class.java)
            intent.putExtra("id", masVendido.id)
            holder.itemView.context.startActivity(intent)

//            showOverview(masVendido.descripcion, masVendido.producto)
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