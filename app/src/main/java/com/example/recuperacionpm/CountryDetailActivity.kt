package com.example.recuperacionpm

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recuperacionpm.model.Country

class CountryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Obtener los datos del Intent de manera segura
        val country = intent.getSerializableExtra("country") as? Country

        if (country?.latlng != null && country.latlng.size >= 2) {
            val lat = country.latlng[0]
            val lng = country.latlng[1]
            val mapUrl = "https://www.openstreetmap.org/?mlat=$lat&mlon=$lng&zoom=6"
            webView.loadUrl(mapUrl)
        } else {
            Toast.makeText(this, "Ubicación no disponible", Toast.LENGTH_SHORT).show()
        }
    }
}


