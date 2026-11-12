package com.example.abbystowerapp.Model

data class Personaje(
    val id: Int,
    val nombre: String,
    val vida: Int,
    val ataque: Int,
    val defensa: Int,
    val descripcion: String,
    val imagen: String   // antes: @DrawableRes val imagen: Int
)