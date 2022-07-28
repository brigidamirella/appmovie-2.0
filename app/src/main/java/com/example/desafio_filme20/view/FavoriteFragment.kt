package com.example.desafio_filme20.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_filme20.databinding.FragmentFavoritesBinding
import com.example.desafio_filme20.util.listeners.MovieListener
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.adapters.MovieAdapter
import com.example.desafio_filme20.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var listener: MovieListener
    private val adapter = MovieAdapter()
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.rvListFilms.layoutManager = LinearLayoutManager(context)
        binding.rvListFilms.adapter = adapter

        listener = object : MovieListener {
            override fun onListClick(filme: Film) {
                val directions = FavoriteFragmentDirections.navFavoritesToNavDetails(filme)
                view?.findNavController()?.navigate(directions)
            }

            override fun onFavorite(filme: Film) {

            }

            override fun undoFavorite(filme: Film) {
                favoriteViewModel.removeFromFavorites(filme)
            }
        }
        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.attachListener(listener)
        favoriteViewModel.favoriteList()
    }

    private fun observe() {
        favoriteViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.updateListener(it)

        })

    }

}