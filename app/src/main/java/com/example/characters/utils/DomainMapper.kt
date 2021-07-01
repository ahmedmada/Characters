package com.example.characters.utils

import com.gnova.data.api.response.CharacterResponse

interface DomainMapper <E, D> {

    fun mapToDomainList(characterDTOS: List<E>): List<CharacterResponse>
}