package com.example.recuperacionpm.model

import java.io.Serializable

data class Country(
    val name: Name,
    val capital: List<String>?,
    val latlng: List<Double>?,
    val borders: List<String>?,
    val flags: Flags,
    val population: Int
) : Serializable

data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>?
) : Serializable

data class NativeName(
    val official: String,
    val common: String
) : Serializable

data class Flags(
    val png: String,
    val svg: String
) : Serializable


