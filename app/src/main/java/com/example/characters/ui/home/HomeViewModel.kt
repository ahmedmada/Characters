package com.example.characters.ui.home

import androidx.lifecycle.viewModelScope
import com.example.characters.utils.CharacterlistState
import com.example.characters.utils.MvRxViewModel
import com.example.characters.utils.Resource
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.airbnb.mvrx.*
import com.example.characters.data.repositories.CharacterRepoImpl
import com.example.characters.utils.Status

import kotlinx.coroutines.*

class HomeViewModel @AssistedInject constructor(
    @Assisted state: CharacterlistState,
    private val movieRepoImpl: CharacterRepoImpl
): MvRxViewModel<CharacterlistState>(state)  {

    init {
        viewModelScope.launch {

//            emit(Resource.loading(data = null))
            setState {
                copy(characters = Resource.loading(null))
            }
            try {
                val v = Resource.success(data = movieRepoImpl.getCharacters(1))
                setState {
                    copy(characters = v)
                }
            } catch (exception: Exception) {
                setState {
                    copy(characters = Resource(Status.ERROR,null,exception.message))
                }
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: CharacterlistState): HomeViewModel
    }

    companion object : MvRxViewModelFactory<HomeViewModel, CharacterlistState> {
        override fun create(viewModelContext: ViewModelContext, state: CharacterlistState): HomeViewModel? {
            val fragment = (viewModelContext as FragmentViewModelContext).fragment<HomeFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }

}