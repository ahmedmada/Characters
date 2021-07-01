package com.gnova.data.mappers

import com.example.characters.utils.DomainMapper
import com.gnova.data.api.response.CharacterResponse
import javax.inject.Inject

class CharacterDTOMapper @Inject constructor() : DomainMapper<CharacterResponse, Character> {

    override fun mapToDomainList(characterDTOS: List<CharacterResponse>): List<CharacterResponse> {
        return characterDTOS;
    }
}