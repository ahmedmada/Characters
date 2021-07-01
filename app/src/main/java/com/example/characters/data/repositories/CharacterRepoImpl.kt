package com.gnova.data.repositories

import com.gnova.data.api.CharacterApi
import com.gnova.data.api.response.CharacterResponse
import com.gnova.data.mappers.CharacterDTOMapper
import com.gnova.domain.repositories.CharacterRepo
import io.reactivex.Observable
import javax.inject.Inject

class CharacterRepoImpl@Inject constructor(
    private val characterApi: CharacterApi,
    private val characterMapper: CharacterDTOMapper,

    ) : CharacterRepo {

    override fun getAllCharacters(page: Int): Observable<List<CharacterResponse>>? {

        return characterApi.getTopRatedMovies(10, page)
            .map {
                characterMapper.mapToDomainList(it)
            }
    }
}