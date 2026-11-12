package com.example.abbystowerapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbystowerapp.Data.PersonajesRepository
import com.example.abbystowerapp.Model.Personaje
import kotlinx.coroutines.launch

sealed interface PersonajesUiState {
    data object Loading : PersonajesUiState
    data class Success(val personajes: List<Personaje>) : PersonajesUiState
    data class Error(val mensaje: String) : PersonajesUiState
}

class PersonajesViewModel : ViewModel() {

    var state by mutableStateOf<PersonajesUiState>(PersonajesUiState.Loading)
        private set

    init {
        cargar()
    }

    fun cargar() {
        viewModelScope.launch {
            state = PersonajesUiState.Loading
            state = PersonajesRepository.obtener().fold(
                onSuccess = { PersonajesUiState.Success(it) },
                onFailure = { error ->
                    Log.e("PersonajesViewModel", "Error cargando personajes", error)
                    PersonajesUiState.Error(error.localizedMessage ?: error.message ?: "Error al conectar con la API")
                }
            )
        }
    }
}