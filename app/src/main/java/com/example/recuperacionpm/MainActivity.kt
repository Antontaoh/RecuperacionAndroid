package com.example.recuperacionpm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.countries.observe(this) { countries ->
            recyclerView.adapter = CountryAdapter(countries) { country ->
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("country", country as Serializable)
                startActivity(intent)
            }
        }

        viewModel.fetchCountries("Europe") // Cargar Europa por defecto
    }
}
