package com.example.desafio_filme20.repository

import android.content.Context
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.model.MovieResult
import com.example.desafio_filme20.database.MovieDataBase
import com.example.desafio_filme20.service.MovieService
import com.example.desafio_filme20.service.RetrofitClient
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieRepository(context: Context) {
    private val client = RetrofitClient.createService(MovieService::class.java)
    private val database = MovieDataBase.getDatabase(context).movieDao()

    fun listFav(): Observable<List<Film>> {
        return database.getFavoriteList()
    }

    fun getPopular(): Observable<MovieResult> {
        return client.getPopularMovies()
    }

    fun getTopRated(): Observable<MovieResult> {
        return client.getTopRatedMovies()
    }
    fun delete(film: Film): Completable {
        return Completable.create {
            try {
                database.delete(film)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun isFavorite(film: Film): Boolean {
        return database.isFavorite(film.id)
    }

    fun saveMovies(film: Film): Completable {
        return Completable.create {
            try {
                database.save(film)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

}