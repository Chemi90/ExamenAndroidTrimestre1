package com.example.examen.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.examen.Pais

class PaisAdapter(
    private var listaCompleta: List<Pais>,
    private var paisesFiltrados: List<Pais>,
    private val onClickListener: (Pais) -> Unit
) : RecyclerView.Adapter<PaisViewHolder>() {

    init {
        paisesFiltrados = ArrayList(listaCompleta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaisViewHolder(layoutInflater.inflate(R.layout.item_pais, parent, false))
    }

    override fun getItemCount(): Int {
        return paisesFiltrados.size
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val item = paisesFiltrados[position]
        holder.render(item, onClickListener)
    }

    fun updateList(newList: List<Pais>) {
        paisesFiltrados = ArrayList(newList)
        notifyDataSetChanged()
    }
}