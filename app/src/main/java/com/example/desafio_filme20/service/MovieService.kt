package com.example.desafio_filme20.service

import com.example.desafio_filme20.BuildConfig
import com.example.desafio_filme20.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ): Observable<MovieResult>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResult
}