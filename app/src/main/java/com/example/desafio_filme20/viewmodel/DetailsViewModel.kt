package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)
    var reactive = CompositeDisposable()

    fun addToFavorites(film: Film) {
        film.favorite = true
        reactive.add(
            repository.saveMovies(film)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        reactive.remove(repository.delete(film).subscribe {
            reactive.clear()
        })
    }

    override fun onCleared() {
        super.onCleared()
        reactive.clear()
    }
}