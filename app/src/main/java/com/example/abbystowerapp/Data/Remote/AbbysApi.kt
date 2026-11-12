package com.example.abbystowerapp.Data.Remote

import retrofit2.http.GET

interface AbbysApi {
    @GET("api/Characters")
    suspend fun getPersonajes(): ApiResponse<List<PersonajeDto>>

    @GET("api/Levels")
    suspend fun getNiveles(): ApiResponse<List<NivelDto>>
}