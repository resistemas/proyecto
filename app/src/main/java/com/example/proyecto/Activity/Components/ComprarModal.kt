package com.example.proyecto.Activity.Components

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.proyecto.Core.Ayudante
import com.example.proyecto.Core.Validaciones
import com.example.proyecto.Model.Productos
import com.example.proyecto.R
import com.example.proyecto.databinding.ModalComprarBinding

class ComprarModal(
    private val Preferencias : Ayudante,
    private val listProductos : List<Productos>,
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

        binding.txtBuyCantidad.setText("1")
        binding.txtBuyTotal.setText("${listProductos.first().precio.toString().toFloat() * 1}")

        binding.txtBuyCantidad.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                if(binding.txtBuyCantidad.text.toString() == ""){
                    binding.txtBuyTotal.setText("${listProductos.first().precio.toString().toFloat() * 1}")
                }else{
                    var cantidad = binding.txtBuyCantidad.text.toString().toInt()
                    if(cantidad == 0) {
                        cantidad = 1
                        binding.txtBuyCantidad.setText("1")
                    }
                    binding.txtBuyTotal.setText("${listProductos.first().precio.toString().toFloat() * cantidad}")
                }

            }

        })

        binding.btnBuy.setOnClickListener {
            val valid = Validaciones().compras(Ciudad.toString(), binding.txtBuyDireccion.text.toString(), binding.txtBuyCantidad.text.toString(), binding.txtBuyTotal.text.toString())
            if(valid != "Valido"){
                Preferencias.showInfo(valid)
            }else{
                onSubmit.invoke(
                    Ciudad.toString(),
                    binding.txtBuyDireccion.text.toString(),
                    binding.txtBuyCantidad.text.toString().toInt(),
                    binding.txtBuyTotal.text.toString().toFloat()
                )
            }
        }

        binding.btnCloseModal.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}