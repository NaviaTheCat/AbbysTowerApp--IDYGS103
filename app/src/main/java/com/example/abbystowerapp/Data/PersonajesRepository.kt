package com.example.abbystowerapp.Data

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.Data
 * Created by: riqui
 * On: 27/10/2026
 * All rights reserved: 2026
 */


import com.example.abbystowerapp.Model.Personaje
import com.example.abbystowerapp.R

object PersonajesRepository {

    private const val DESCRIPCION_PRUEBA =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

    val personajes: List<Personaje> = List(8) { i ->
        Personaje(
            id = i,
            nombre = "Nombre Personaje",
            vida = 20 + i,
            ataque = 14 + i,
            defensa = 8 + i,
            descripcion = DESCRIPCION_PRUEBA,
            imagen = R.drawable.monster_down
        )
    }

    fun porId(id: Int): Personaje? = personajes.firstOrNull { it.id == id }
}