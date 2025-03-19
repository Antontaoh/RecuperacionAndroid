package com.example.recuperacionpm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recuperacionpm.model.Country
import com.example.recuperacionpm.service.CountryApiService

class CountryViewModel : ViewModel() {
    private val apiService = CountryApiService.create()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    fun fetchCountries(region: String) {
        viewModelScope.launch {
            try {
                val result = when (region) {
                    "Europe" -> apiService.getEuropeanCountries()
                    "America" -> apiService.getAmericanCountries()
                    "Africa" -> apiService.getAfricanCountries()
                    "Asia" -> apiService.getAsianCountries()
                    "Oceania" -> apiService.getOceanianCountries()
                    else -> emptyList()
                }

                _countries.postValue(result) // ✅ Usar postValue para actualización segura en LiveData

            } catch (e: Exception) {
                Log.e("CountryViewModel", "Error al obtener países", e) // ✅ Mejor log de error
                _countries.postValue(emptyList())
            }
        }
    }
}

