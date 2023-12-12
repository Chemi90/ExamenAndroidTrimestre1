package com.example.examen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.databinding.ActivityDosBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDosBinding
    private lateinit var pais: Pais
    private lateinit var miDAO: PaisDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        miDAO = PaisDAO()

        val posicion = intent.getIntExtra("posicion", -1)

        if (posicion != -1) {
            pais = obtenerPaisDesdeLista(posicion)

            configurarInterfaz(pais)
        } else {
            Toast.makeText(this, "Error al obtener la información del país", Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        binding.volverBtn.setOnClickListener {
            finish()
        }
    }

    private fun obtenerPaisDesdeLista(posicion: Int): Pais {
        val listaPaises = miDAO.cargarLista(this)
        return listaPaises[posicion]
    }

    private fun configurarInterfaz(pais: Pais) {
        binding.nombrePais.text = pais.nombre
        binding.banderaPais.setImageResource(pais.imagen)
        if (pais.pertenece) {
            binding.UE.setImageResource(R.drawable.europe)
        } else {
            binding.UE.setImageResource(R.drawable.europa)
        }
        binding.infoUE.text =
            if (pais.pertenece) "País miembro de la Unión Europea" else "País NO miembro de la Unión Europea"
        binding.mapa.setImageResource(pais.imagenMapa)
        binding.habitantesText.text = "Habitantes: ${pais.habitantes}"
        binding.capitalText.text = "Capital: ${pais.capital}"

    }
}