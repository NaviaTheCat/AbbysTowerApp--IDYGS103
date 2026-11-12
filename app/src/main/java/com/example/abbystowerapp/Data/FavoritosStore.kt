package com.example.abbystowerapp.Data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favoritos")

object FavoritosStore {

    private val CLAVE = stringSetPreferencesKey("ids_favoritos")

    // Lee los ids guardados; se actualiza solo cuando cambian
    fun favoritos(context: Context): Flow<Set<Int>> =
        context.dataStore.data.map { prefs ->
            prefs[CLAVE].orEmpty().mapNotNull { it.toIntOrNull() }.toSet()
        }

    // Agrega el id si no está, o lo quita si ya estaba
    suspend fun alternar(context: Context, id: Int) {
        context.dataStore.edit { prefs ->
            val actuales = prefs[CLAVE].orEmpty()
            val clave = id.toString()
            prefs[CLAVE] = if (clave in actuales) actuales - clave else actuales + clave
        }
    }
}