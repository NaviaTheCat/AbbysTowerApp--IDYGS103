package com.example.abbystowerapp.Model

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.Model
 * Created by: riqui
 * On: 27/10/2026
 * All rights reserved: 2026
 */


import androidx.annotation.DrawableRes

data class Personaje(
    val id: Int,
    val nombre: String,
    val vida: Int,
    val ataque: Int,
    val defensa: Int,
    val descripcion: String,
    @DrawableRes val imagen: Int
)