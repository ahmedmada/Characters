package com.example.characters.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.characters.ui.home.HomeViewState.*
import com.gnova.data.repositories.CharacterRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val movieRepoImpl: CharacterRepoImpl): ViewModel()  {


    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState>
        get() = _viewState

    fun onViewLoaded(page : Int) {
        getTopRatedMovies(page)
    }


    private fun getTopRatedMovies(page : Int) {

        _viewState.value = Loading
        add(
            movieRepoImpl.getAllCharacters(page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    _viewState.value = Presenting(it)
                }, {
                    onError(it)
                    _viewState.value = Error
                }
                )
        )
    }


    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun add(disposable: Disposable?) {
        if (disposable != null) {
            compositeDisposable.add(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}