package com.example.recuperacionpm
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recuperacionpm.databinding.ActivityCountryDetailBinding
import com.example.recuperacionpm.model.Country
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CountryDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityCountryDetailBinding
    private lateinit var mMap: GoogleMap
    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        country = intent.getSerializableExtra("country") as Country

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val location = LatLng(country.latlng?.get(0) ?: 0.0, country.latlng?.get(1) ?: 0.0)
        mMap.addMarker(MarkerOptions().position(location).title(country.name["spa"]))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))
    }
}
