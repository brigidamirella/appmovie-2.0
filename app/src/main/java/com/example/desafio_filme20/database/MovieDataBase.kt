package com.example.desafio_filme20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.desafio_filme20.model.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private lateinit var INSTANCE: MovieDataBase
        fun getDatabase(context: Context): MovieDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, "filmDB")
                    .build()
            }
            return INSTANCE
        }
    }

}