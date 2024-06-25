package com.example.proyecto.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.proyecto.Activity.DetalleActivity
import com.example.proyecto.Activity.FavoritoActivity
import com.example.proyecto.Model.Favorito
import com.example.proyecto.Model.Productos
import com.example.proyecto.R
import com.example.proyecto.ViewModel.FavoritoViewModel

class FavoritoAdapter(var context: Context, var favoritoLista : List<Favorito>, var viewModel: FavoritoViewModel) : RecyclerView.Adapter<FavoritoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFavList = itemView.findViewById(R.id.ivFavoritoLista) as ImageView
        val btnShopFavList = itemView.findViewById(R.id.btnShopFavoritoLista) as ImageView
        val btnDestroyFavList = itemView.findViewById(R.id.btnDestroyFavoritoLista) as ImageView
        val tvTituloFavList = itemView.findViewById(R.id.txtTituloFavoritoLista) as TextView
        val tvDescripcionFavList = itemView.findViewById(R.id.txtDescripcionFavoritoLista) as TextView
        val tvArtesanoFavList = itemView.findViewById(R.id.txtArtesanoFavoritoLista) as TextView
        val tvCategoriaFavList = itemView.findViewById(R.id.txtCategoriaFavoritoLista) as TextView
        val tvPrecioFavList = itemView.findViewById(R.id.txtPrecioFavoritoLista) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_lista_favorito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorito = favoritoLista[position]

        Glide.with(context).load(favorito.producto?.photo_video)
            .override(120, 180)
            .transforms(RoundedCorners(15))
            .into(holder.ivFavList)

        holder.ivFavList.setBackgroundResource(R.drawable.img_rouended)

        holder.tvTituloFavList.text = favorito.producto?.producto
        holder.tvDescripcionFavList.text = favorito.producto?.descripcion
        holder.tvArtesanoFavList.text = favorito.producto?.vendedor?.nombresApellidos
        holder.tvCategoriaFavList.text = favorito.producto?.categoria?.categoria
        holder.tvPrecioFavList.text = "S/. ${favorito.producto?.precio}"

        holder.btnShopFavList.setOnClickListener {
            val intent = Intent(context, DetalleActivity::class.java)
            intent.putExtra("id", favorito.producto?.id)
            intent.putExtra("categoria", favorito.producto?.categoriaId)
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDestroyFavList.setOnClickListener {
            viewModel.deleteFavorito(favorito.id.toString())
            val intent = Intent(context, FavoritoActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = favoritoLista.size
}