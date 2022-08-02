package com.example.desafio_filme20.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_filme20.adapters.MovieAdapter
import com.example.desafio_filme20.databinding.FragmentTopRatedBinding
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.util.listeners.MovieListener
import com.example.desafio_filme20.viewmodel.TopRatedViewModel

class TopRatedFragment : Fragment() {

    private lateinit var viewModel: TopRatedViewModel
    private lateinit var listener: MovieListener
    private val adapter = MovieAdapter()

    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(TopRatedViewModel::class.java)


        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        binding.rvListFilms.layoutManager = LinearLayoutManager(context)
        binding.rvListFilms.adapter = adapter

        listener = object : MovieListener {
            override fun onListClick(filme: Film) {
                val directions =
                    TopRatedFragmentDirections.actionNavTopRatedToNavDetails(filme)
                findNavController().navigate(directions)
            }

            override fun onFavorite(filme: Film) {
                viewModel.addToFavorites(filme)
                filme.favorite = true
            }

            override fun undoFavorite(filme: Film) {
                viewModel.removeFromFavorites(filme)
                filme.favorite = false
            }
        }
        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.attachListener(listener)
        viewModel.getTopRatedMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.updateListener(it)
        })

    }
}