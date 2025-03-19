package com.example.recuperacionpm.service

import com.example.recuperacionpm.model.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryApiService {
    @GET("region/europe?fields=name,capital,latlng,borders,flags,population")
    suspend fun getEuropeanCountries(): List<Country>

    @GET("region/america?fields=name,capital,latlng,borders,flags,population")
    suspend fun getAmericanCountries(): List<Country>

    @GET("region/africa?fields=name,capital,latlng,borders,flags,population")
    suspend fun getAfricanCountries(): List<Country>

    @GET("region/asia?fields=name,capital,latlng,borders,flags,population")
    suspend fun getAsianCountries(): List<Country>

    @GET("region/oceania?fields=name,capital,latlng,borders,flags,population")
    suspend fun getOceanianCountries(): List<Country>

    companion object {
        fun create(): CountryApiService {
            return Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountryApiService::class.java)
        }
    }
}
