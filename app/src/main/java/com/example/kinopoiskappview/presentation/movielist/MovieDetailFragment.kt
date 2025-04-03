package com.example.kinopoiskappview.presentation.movielist

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.databinding.FragmentMovieDetailBinding
import com.example.kinopoiskappview.domain.model.Movie

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailBinding == null")

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireActivity())
            .load(movie.posterUrl)
            .into(binding.posterImageView)
        binding.titleTextView.text = movie.name
        binding.yearTextView.text = movie.year
        binding.descriptionTextView.text = movie.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseParams() {
        movie = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(MOVIE_PARAM, Movie::class.java)
        } else requireArguments().getSerializable(MOVIE_PARAM) as Movie)
            ?: throw IllegalArgumentException("Movie parameter is missing")
    }

    companion object {

        private const val MOVIE_PARAM = "MOVIE_PARAM"

        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MOVIE_PARAM, movie)
                }
            }
    }
}