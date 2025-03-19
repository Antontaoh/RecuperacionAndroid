package com.example.recuperacionpm

import CountryAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔹 Inicializar el adapter con click normal y click largo
        adapter = CountryAdapter(emptyList(),
            onClick = { country ->
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("country", country)
                startActivity(intent)
            },
            onLongClick = { country ->
                val intent = Intent(this, CountryInfoActivity::class.java)
                intent.putExtra("country", country)
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter

        // 🔹 Optimización: Actualizar la lista sin recrear el adapter
        viewModel.countries.observe(this) { countries ->
            adapter = CountryAdapter(countries,
                onClick = { country ->
                    val intent = Intent(this, CountryDetailActivity::class.java)
                    intent.putExtra("country", country)
                    startActivity(intent)
                },
                onLongClick = { country ->
                    val intent = Intent(this, CountryInfoActivity::class.java)
                    intent.putExtra("country", country)
                    startActivity(intent)
                }
            )
            recyclerView.adapter = adapter
        }

        viewModel.fetchCountries("Europe") // Cargar Europa por defecto
    }
}


