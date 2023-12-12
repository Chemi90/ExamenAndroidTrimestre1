package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.examen.databinding.ActivityMainBinding
import com.example.examen.Adapter.PaisAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaPaises: MutableList<Pais>
    private lateinit var miDAO: PaisDAO
    private lateinit var layoutManager: LayoutManager
    private lateinit var adapter: PaisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        miDAO = PaisDAO()
        listaPaises = miDAO.cargarLista(this)
        Log.d("MainActivity", "Número de elementos en la lista: ${listaPaises.size}")

        layoutManager = LinearLayoutManager(this)
        binding.rvPaises.layoutManager = layoutManager

        adapter = PaisAdapter(listaPaises, listaPaises) { pais ->
            onItemSelected(pais)
        }
        binding.rvPaises.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.rvPaises.setHasFixedSize(true)
        binding.rvPaises.itemAnimator = DefaultItemAnimator()
    }

    private fun onItemSelected(pais: Pais) {
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("posicion", listaPaises.indexOf(pais))
        this.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.unionEuropea -> {
                val filteredList = listaPaises.filter { it.pertenece }
                adapter.updateList(filteredList)
                return true
            }

            R.id.restoPaises -> {
                val filteredList = listaPaises.filter { !it.pertenece }
                adapter.updateList(filteredList)
                return true
            }

            R.id.todosPaises -> {
                adapter.updateList(listaPaises)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}