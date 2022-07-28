package com.example.desafio_filme20.service

import com.example.desafio_filme20.util.constants.MovieConstants
import com.example.desafio_filme20.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular?api_key=${MovieConstants.API.key}")
    fun getPopularMovies(): Observable<MovieResult>

    @GET("search/movie?api_key=${MovieConstants.API.key}")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResult
}