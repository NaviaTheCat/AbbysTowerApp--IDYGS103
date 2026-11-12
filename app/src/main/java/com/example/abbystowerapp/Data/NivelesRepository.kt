package com.example.abbystowerapp.Data

import com.example.abbystowerapp.Data.Remote.ApiClient
import com.example.abbystowerapp.Model.Niveles

object NivelesRepository {

    suspend fun obtener(): Result<List<Niveles>> = runCatching {
        val resp = ApiClient.api.getNiveles()
        if (!resp.succeded) error(resp.message ?: "Error en la API")

        resp.result.orEmpty().map {
            Niveles(
                id = it.idLevel,
                Nombre = it.nombre,
                descripcion = it.descripcion,
                Imagen = ApiClient.BASE_URL.trimEnd('/') + it.imagen.replace(" ", "%20")
            )
        }
    }
}