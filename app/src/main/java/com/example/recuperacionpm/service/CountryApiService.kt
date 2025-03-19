package com.example.recuperacionpm.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryApiService {
    @GET("region/europe?fields=translations,capital,latlng,borders,flags,population")
    suspend fun getEuropeanCountries(): List<Country>

    @GET("region/america?fields=translations,capital,latlng,borders,flags,population")
    suspend fun getAmericanCountries(): List<Country>

    @GET("region/africa?fields=translations,capital,latlng,borders,flags,population")
    suspend fun getAfricanCountries(): List<Country>

    @GET("region/asia?fields=translations,capital,latlng,borders,flags,population")
    suspend fun getAsianCountries(): List<Country>

    @GET("region/oceania?fields=translations,capital,latlng,borders,flags,population")
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
