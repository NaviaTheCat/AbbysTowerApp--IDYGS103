package com.example.abbystowerapp.Data.Remote

data class PersonajeDto(
    val idCharacter: Int,
    val nombre: String,
    val descripcion: String,
    val vida: Int,
    val ataque: Int,
    val defensa: Int,
    val imagen: String
)