package com.example.examen.Database

import android.provider.BaseColumns

class PaisContract {
    companion object {
        const val NOMBRE_BD = "paises"
        const val VERSION = 1

        class Entrada : BaseColumns {
            companion object {
                const val NOMBRE_TABLA = "paises"
                const val COLUMNA_ID = "id"
                const val COLUMNA_NOMBRE = "nombre"
                const val COLUMNA_IMAGEN = "imagen"
                const val COLUMNA_IMAGEN_EEUU = "imagenEEUU"
                const val COLUMNA_IMAGEN_MAPA = "imagenMAPA"
                const val COLUMNA_HABITANTES = "habitantes"
                const val COLUMNA_CAPITAL = "capital"
                const val COLUMNA_PERTENECE_EEUU = "pertenece"
            }
        }
    }
}