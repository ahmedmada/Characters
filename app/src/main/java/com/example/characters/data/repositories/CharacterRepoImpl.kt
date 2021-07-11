package com.example.characters.data.repositories

import com.example.characters.data.api.CharacterApi
import com.example.characters.data.api.response.CharacterResponse
import javax.inject.Inject

class CharacterRepoImpl@Inject constructor(
    private val characterApi: CharacterApi,
    ) {

    suspend fun getCharacters(page: Int):List<CharacterResponse> ?{
        return characterApi.getCharacters(10, page)
    }
}
