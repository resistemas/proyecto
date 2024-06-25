package com.example.proyecto.Activity.Components

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.proyecto.R
import com.example.proyecto.databinding.ModalComprarBinding

class ComprarModal(
    private val onSubmit : (String, String, Int, Float) -> Unit
) : DialogFragment() {
    private lateinit var binding : ModalComprarBinding
    private var listaCiudad : List<String> = listOf("AMAZONAS","ANCASH","APURIMAC", "AREQUIPA","AYACUCHO","CAJAMARCA","CUSCO","HUANCAVELICA","HUANUCO","ICA","JUNIN","LA LIBERTAD","LAMBAYEQUE","LIMA","LORETO","MADRE DE DIOS","MOQUEGUA","PASCO","PIURA","PUNO","SAN MARTIN","TACNA","TUMBES","UCAYALI")
    private var Ciudad : String = ""


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ModalComprarBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val listDrop = binding.AutoCompraCiudad
        val adapter = ArrayAdapter(requireContext(), R.layout.list_items, listaCiudad)
        listDrop.setAdapter(adapter)
        listDrop.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)
            Ciudad = itemSelected.toString()
        }

        binding.btnBuy.setOnClickListener {
            onSubmit.invoke(
                Ciudad.toString(),
                binding.txtBuyDireccion.text.toString(),
                binding.txtBuyCantidad.text.toString().toInt(),
                binding.txtBuyTotal.text.toString().toFloat()
            )
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}