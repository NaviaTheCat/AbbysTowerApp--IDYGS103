package com.example.abbystowerapp.Data

import com.example.abbystowerapp.Data.Remote.ApiClient
import com.example.abbystowerapp.Model.Personaje

object PersonajesRepository {

    private var cache: List<Personaje> = emptyList()

    suspend fun obtener(): Result<List<Personaje>> = runCatching {
        val resp = ApiClient.api.getPersonajes()
        if (!resp.succeded) error(resp.message ?: "Error en la API")

        resp.result.orEmpty().map {
            Personaje(
                id = it.idCharacter,
                nombre = it.nombre,
                vida = it.vida,
                ataque = it.ataque,
                defensa = it.defensa,
                descripcion = it.descripcion,
                imagen = ApiClient.BASE_URL.trimEnd('/') + it.imagen
            )
        }.also { cache = it }
    }

    fun porId(id: Int): Personaje? = cache.firstOrNull { it.id == id }
}