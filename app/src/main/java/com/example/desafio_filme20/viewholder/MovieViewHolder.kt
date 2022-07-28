package com.example.desafio_filme20.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.RowListFilmsBinding
import com.example.desafio_filme20.util.constants.MovieConstants
import com.example.desafio_filme20.util.listeners.MovieListener
import com.example.desafio_filme20.model.Film
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MovieViewHolder(
    private val binding: RowListFilmsBinding,
    private val listener: MovieListener,
    var filme: Film? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    private val baseUrlFilme = MovieConstants.API.baseUrlFilme200

    fun setMovieData(film: Film) {
        binding.movieTitle.text = film.title
        binding.movieReleaseDate.text = film.release_date
        binding.txtRatingCount.text = film.vote_average.toString()
        Picasso.get().load(baseUrlFilme + film.poster_path).into(binding.moviePoster)
        if (film.favorite) {
            binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite_red)
        } else {
            binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite_black)
        }
        binding.body.setOnClickListener { listener.onListClick(film) }
        binding.btnFavoriteFilm.setOnClickListener {
            if (film.favorite) {
                listener.undoFavorite(film)
                binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite_black)
                val snackBar = Snackbar.make(it, "Removido dos favoritos", 1000)
                snackBar.setBackgroundTint(Color.RED)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.setAction("Close", View.OnClickListener {
                }).show()
            } else {
                listener.onFavorite(film)
                binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite_red)
                val snackBar = Snackbar.make(it, "Adicionado aos favoritos", 1000)
                snackBar.setBackgroundTint(Color.BLUE)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.setAction("Close", View.OnClickListener {
                }).show()
            }
        }


    }


}