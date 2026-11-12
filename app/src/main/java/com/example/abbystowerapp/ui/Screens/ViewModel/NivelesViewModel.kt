package com.example.abbystowerapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbystowerapp.Data.NivelesRepository
import com.example.abbystowerapp.Model.Niveles
import kotlinx.coroutines.launch

sealed interface NivelesUiState {
    data object Loading : NivelesUiState
    data class Success(val niveles: List<Niveles>) : NivelesUiState
    data class Error(val mensaje: String) : NivelesUiState
}

class NivelesViewModel : ViewModel() {

    var state by mutableStateOf<NivelesUiState>(NivelesUiState.Loading)
        private set

    init {
        cargar()
    }

    fun cargar() {
        viewModelScope.launch {
            state = NivelesUiState.Loading
            state = NivelesRepository.obtener().fold(
                onSuccess = { NivelesUiState.Success(it) },
                onFailure = { NivelesUiState.Error(it.message ?: "No se pudo conectar") }
            )
        }
    }
}