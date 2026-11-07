package com.example.abbystowerapp.Model

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.Model
 * Created by: karla
 * On: 05/11/2026
 * All rights reserved: 2026
 */



enum class Orden(val etiqueta: String) {
    NINGUNO("Sin orden"),
    NOMBRE("Nombre A-Z"),
    VIDA("Mayor vida"),
    ATAQUE("Mayor ataque"),
    DEFENSA("Mayor defensa")
}

data class Filtro(
    val orden: Orden = Orden.NINGUNO,
    val minVida: Int = 0,
    val minAtaque: Int = 0,
    val minDefensa: Int = 0
) {

    val activo: Boolean get() = this != Filtro()
}

fun List<Personaje>.aplicar(filtro: Filtro, texto: String): List<Personaje> {
    val filtrados = filter {
        it.nombre.contains(texto, ignoreCase = true) &&
                it.vida >= filtro.minVida &&
                it.ataque >= filtro.minAtaque &&
                it.defensa >= filtro.minDefensa
    }
    return when (filtro.orden) {
        Orden.NINGUNO -> filtrados
        Orden.NOMBRE -> filtrados.sortedBy { it.nombre.lowercase() }
        Orden.VIDA -> filtrados.sortedByDescending { it.vida }
        Orden.ATAQUE -> filtrados.sortedByDescending { it.ataque }
        Orden.DEFENSA -> filtrados.sortedByDescending { it.defensa }
    }
}