package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)
    private val listMovie = MutableLiveData<List<Film>>()
    var reactive = CompositeDisposable()
    var list: LiveData<List<Film>> = listMovie


    fun getTopRatedMovies() {
        reactive.add(
            repository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    listMovie.postValue(checkFavorite(it.results))
                }, { e ->
                    e.printStackTrace()
                })
        )
    }

    private fun checkFavorite(filmList: List<Film>): List<Film> {
        filmList.forEach { it.favorite = repository.isFavorite(it) }
        return filmList
    }

    fun addToFavorites(film: Film) {
        film.favorite = true
        reactive.add(
            repository.saveMovies(film).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe {})
    }

    fun removeFromFavorites(film: Film) {
        film.favorite = false
        reactive.add(repository.delete(film).subscribe {reactive.clear()})
    }

    override fun onCleared() {
        super.onCleared()
        reactive.clear()
    }
}