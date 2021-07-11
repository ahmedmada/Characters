package com.example.characters.utils

import com.airbnb.mvrx.MvRxState
import com.example.characters.data.api.response.CharacterResponse

data class CharacterlistState(
    val characters: Resource<List<CharacterResponse>?> = Resource.loading(null)
//    val characters: Async<List<CharacterResponse>> = Uninitialized
): MvRxState
