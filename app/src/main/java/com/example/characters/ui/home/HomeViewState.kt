package com.example.characters.ui.home

import com.gnova.data.api.response.CharacterResponse


sealed class HomeViewState {

    data class Presenting(val results: List<CharacterResponse>) : HomeViewState()

    object Error : HomeViewState()

    object Loading : HomeViewState()
}