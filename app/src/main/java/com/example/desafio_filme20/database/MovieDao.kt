package com.example.desafio_filme20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.desafio_filme20.model.Film
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert
    fun save(film: Film)

    @Delete
    fun delete(film: Film)

    //    Favoritos organizados pelo titulo
    @Query("SELECT * FROM Film ORDER BY original_title ASC")
    fun getFavoriteList(): Observable<List<Film>>

    @Query("SELECT favorite FROM Film WHERE id = :id")
    fun isFavorite(id: Int): Boolean
}