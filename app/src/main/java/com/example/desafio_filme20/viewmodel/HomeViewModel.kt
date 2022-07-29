package com.example.desafio_filme20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    /* TODO 6
        Limpar o reactive(Composite Disposable) após a operação chamada acabar de ser executada para evitar leaks
     */


    private val repository = MovieRepository(application)
    private val listMovie = MutableLiveData<List<Film>>()
    var reactive = CompositeDisposable()
    var list: LiveData<List<Film>> = listMovie


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
        reactive.add(repository.delete(film).subscribe {})
    }

    fun getMovies() {
        reactive.add(
            repository.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    listMovie.postValue(checkFavorite(it.results))
                }, { e ->
                    e.printStackTrace()
                })
        )
    }


}