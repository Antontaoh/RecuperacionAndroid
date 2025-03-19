package com.example.recuperacionpm

import CountryAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recuperacionpm.model.Country
import com.example.recuperacionpm.service.CountryApiService

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔹 Inicializar el adapter una sola vez y actualizar la lista después
        adapter = CountryAdapter(emptyList()) { country ->
            val intent = Intent(this, CountryDetailActivity::class.java)
            intent.putExtra("country", country) // ✅ Si `Country` es Parcelable, no necesitas `as Serializable`
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // 🔹 Optimización: En lugar de crear un nuevo Adapter, solo actualizamos los datos
        viewModel.countries.observe(this) { countries ->
            adapter = CountryAdapter(countries) { country ->
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("country", country)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        viewModel.fetchCountries("Europe") // Cargar Europa por defecto
    }
}

