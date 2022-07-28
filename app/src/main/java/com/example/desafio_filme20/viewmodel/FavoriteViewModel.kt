package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)
    private val listMovie = MutableLiveData<List<Film>>()
    val reactive = CompositeDisposable()
    var list: LiveData<List<Film>> = listMovie

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        reactive.add(repository.delete(film).subscribe {})
    }

    fun favoriteList() {
        reactive.add(
            repository.listFav()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    listMovie.postValue(it)
                }, { e ->
                    e.printStackTrace()
                })
        )
    }

}