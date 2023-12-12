package com.example.examen

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.examen.Database.DBOpenHelper
import com.example.examen.Database.PaisContract

class PaisDAO {
    fun cargarLista(context: Context?): MutableList<Pais> {
        lateinit var res: MutableList<Pais>
        lateinit var c: Cursor
        try {
            // Obtener acceso de solo lectura
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            val columnas = arrayOf(
                PaisContract.Companion.Entrada.COLUMNA_ID,
                PaisContract.Companion.Entrada.COLUMNA_NOMBRE,
                PaisContract.Companion.Entrada.COLUMNA_IMAGEN,
                PaisContract.Companion.Entrada.COLUMNA_IMAGEN_EEUU,
                PaisContract.Companion.Entrada.COLUMNA_IMAGEN_MAPA,
                PaisContract.Companion.Entrada.COLUMNA_HABITANTES,
                PaisContract.Companion.Entrada.COLUMNA_CAPITAL,
                PaisContract.Companion.Entrada.COLUMNA_PERTENECE_EEUU
            )
            c = db.query(
                PaisContract.Companion.Entrada.NOMBRE_TABLA,
                columnas, null, null, null, null, null
            )
            res = mutableListOf()
            while (c.moveToNext()) {
                val nueva = Pais(
                    c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3),
                    c.getInt(4), c.getString(5), c.getString(6), c.getInt(7) != 0
                )
                res.add(nueva)
            }
            Log.d("PaisDAO", "Número de elementos en la listaaaaa: ${res.size}")

        } finally {
            c.close()
        }
        return res
    }
}