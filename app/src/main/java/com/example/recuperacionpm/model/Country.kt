package com.example.recuperacionpm.model
import java.io.Serializable

data class Country(
    val name: Map<String, String>,
    val capital: List<String>?,
    val latlng: List<Double>?,
    val borders: List<String>?,
    val flags: Map<String, String>,
    val population: Int
) : Serializable

