package com.example.desafio_filme20.view


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.FragmentDetailsBinding
import com.example.desafio_filme20.model.Film
import com.example.desafio_filme20.util.constants.MovieConstants
import com.example.desafio_filme20.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar


class DetailsFragment : Fragment() {
    private lateinit var detailsViewModel: DetailsViewModel
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        val bundle = arguments
        if (bundle != null) {
            val movie = bundle.getParcelable<Film>("filme")
            if (movie != null) {
                getMovieData(movie)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMovieData(film: Film) {
        var baseUrlFilme = MovieConstants.API.baseUrlFilme500
        binding.movieTitle.text = film.title
        binding.movieReleaseDate.text = "Pub: ${film.release_date}"
        binding.txtOverView.text = film.overview
        binding.txtRatingCount.text = film.vote_average.toString()
        Glide.with(this)
            .load(baseUrlFilme + film.backdrop_path)
            .centerCrop()
            .into(binding.moviePoster)
        if (film.favorite) {
            binding.iconFavorite.setImageResource(R.drawable.ic_favorite_red)
        } else {
            binding.iconFavorite.setImageResource(R.drawable.ic_favorite_black)
        }

        binding.iconFavorite.setOnClickListener {
            if (film.favorite) {
                binding.iconFavorite.setImageResource(R.drawable.ic_favorite_black)
                detailsViewModel.removeFromFavorites(film)
                //                val toast = Toast.makeText(context,
//                    "Removido dos favoritos",
//                    Toast.LENGTH_SHORT)
//                    .show()
                val snackBar = Snackbar.make(it, "Removido dos favoritos", 1000)
                snackBar.setBackgroundTint(Color.RED)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.setAction("Close", View.OnClickListener {
                }).show()

            } else {
                detailsViewModel.addToFavorites(film)
                binding.iconFavorite.setImageResource(R.drawable.ic_favorite_red)

//                val toast = Toast.makeText(context,
//                    "Adicionado aos favoritos",
//                    Toast.LENGTH_SHORT)
//                    .show()
                val snackBar = Snackbar.make(it, "Adicionado aos favoritos", 1000)
                snackBar.setBackgroundTint(Color.BLUE)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.setAction("Close", View.OnClickListener {
                }).show()

            }
        }


    }

}