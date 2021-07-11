package com.example.characters.data.api

import com.example.characters.data.api.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") page: Int
    ): List<CharacterResponse>

}