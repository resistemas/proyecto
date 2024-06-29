package com.example.proyecto.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Activity.DetalleActivity
import com.example.proyecto.Model.Venta
import com.example.proyecto.R

class VentaAdapter(
    var context: Context, var ventasLista : List<Venta>,
    private val onSubmit : (String, String) -> Unit
) : RecyclerView.Adapter<VentaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtShopcodigo = itemView.findViewById(R.id.txtShopCodigo) as TextView
        val txtShopItem = itemView.findViewById(R.id.txtShopItems) as TextView
        val btnShopAcepted = itemView.findViewById(R.id.btnShopAcepted) as ImageView
        val btnShopCanceled = itemView.findViewById(R.id.btnShopCanceled) as ImageView
        val imgStatus = itemView.findViewById(R.id.imageView8) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_lista_shop, parent, false)
        return VentaAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = ventasLista.size

    override fun onBindViewHolder(holder: VentaAdapter.ViewHolder, position: Int) {
        val item = ventasLista[position]

        holder.txtShopcodigo.text = "${item.codigo}"
        holder.txtShopItem.text = "${item.detalle?.cantidad} Items | S/. ${item.detalle?.total} | ${item.estado}"

        if(item.estado == "Completado"){
            holder.txtShopcodigo.text = "${item.codigo} - ${item.artesano?.nombresApellidos}"
            holder.btnShopAcepted.isFocusable = false
            holder.btnShopAcepted.isFocusableInTouchMode = false
            holder.btnShopAcepted.isClickable = false
            holder.btnShopCanceled.visibility = View.GONE
        }else if( item.estado == "Cancelado"){
            holder.txtShopcodigo.text = "${item.codigo} - ${item.artesano?.nombresApellidos}"
            holder.btnShopCanceled.isFocusable = false
            holder.btnShopCanceled.isFocusableInTouchMode = false
            holder.btnShopCanceled.isClickable = false
            holder.btnShopAcepted.visibility = View.GONE
        }else{
            holder.btnShopAcepted.setOnClickListener {
                onSubmit.invoke(item.id.toString(), "Completado")
            }

            holder.btnShopCanceled.setOnClickListener {
                onSubmit.invoke(item.id.toString(), "Cancelado")
            }
        }


    }
}