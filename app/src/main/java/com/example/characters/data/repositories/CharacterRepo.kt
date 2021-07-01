package com.gnova.domain.repositories

import com.gnova.data.api.response.CharacterResponse
import io.reactivex.Observable

interface CharacterRepo {

    fun getAllCharacters(page: Int): Observable<List<CharacterResponse>>?

}