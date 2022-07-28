package com.example.desafio_filme20.util.listeners

import com.example.desafio_filme20.model.Film


interface MovieListener {
    fun onListClick(filme: Film)

    fun onFavorite(filme: Film)

    fun undoFavorite(filme: Film)
}