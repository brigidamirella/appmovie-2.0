package com.example.desafio_filme20.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.databinding.RowListFilmsBinding
import com.example.desafio_filme20.util.listeners.MovieListener
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.viewholder.MovieViewHolder

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private var listMovie: List<Film> = arrayListOf()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val rowListFilmsBinding =
            RowListFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(rowListFilmsBinding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setMovieData(listMovie[position])

    }

    fun attachListener(listener: MovieListener) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateListener(list: List<Film>) {
        listMovie = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return listMovie.count()
    }

}