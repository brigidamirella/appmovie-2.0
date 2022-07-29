package com.example.desafio_filme20.util.constants

class MovieConstants {

    object API {
        /*
          TODO - 4
           Chaves de Api devem não podem ser salvas no código
           como ocultar -> https://acervolima.com/como-ocultar-api-e-chaves-secretas-no-android-studio/ ou
           https://stackoverflow.com/a/51582501/8390528=
         */
        const val key = "7768c7c1831141e5456595624be2d1c7"

        const val baseUrl = "https://api.themoviedb.org/3/movie/"
        const val baseUrlFilme200 = "https://image.tmdb.org/t/p/w200"
        const val baseUrlFilme500 = "https://image.tmdb.org/t/p/w500"
    }
}