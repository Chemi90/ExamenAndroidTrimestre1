package com.example.examen.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.databinding.ItemPaisBinding
import com.example.examen.Pais

class PaisViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPaisBinding.bind(view)
    private lateinit var pais: Pais

    fun render(item: Pais, onClickListener: (Pais) -> Unit) {
        pais = item
        binding.tvPaisNombre.text = item.nombre
        Glide.with(binding.ivPais.context).load(item.imagen).into(binding.ivPais)
        Glide.with(binding.ivEuropa.context).load(item.imagenEEUU).into(binding.ivEuropa)
        itemView.setOnClickListener {
            onClickListener(item)
        }
    }
}