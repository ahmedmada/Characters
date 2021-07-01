package com.gnova.data.api

import com.gnova.data.api.response.CharacterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/characters")
    fun getTopRatedMovies(
        @Query("limit") limit: Int,
        @Query("offset") page: Int
    ): Observable<List<CharacterResponse>>

}