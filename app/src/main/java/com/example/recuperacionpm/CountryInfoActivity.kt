package com.example.recuperacionpm

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recuperacionpm.model.Country

class CountryInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        val country = intent.getSerializableExtra("country") as? Country

        val countryName = findViewById<TextView>(R.id.countryName)
        val countryCapital = findViewById<TextView>(R.id.countryCapital)
        val countryPopulation = findViewById<TextView>(R.id.countryPopulation)
        val countryBorders = findViewById<TextView>(R.id.countryBorders)
        val flagImage = findViewById<ImageView>(R.id.flagImage)

        country?.let {
            countryName.text = "Nombre: ${it.name.common}"
            countryCapital.text = "Capital: ${it.capital?.joinToString() ?: "No disponible"}"
            countryPopulation.text = "Población: ${it.population}"
            countryBorders.text = "Fronteras: ${it.borders?.joinToString() ?: "Ninguna"}"

            Glide.with(this)
                .load(it.flags.png)
                .placeholder(R.drawable.default_flag)
                .into(flagImage)
        }
    }
}
