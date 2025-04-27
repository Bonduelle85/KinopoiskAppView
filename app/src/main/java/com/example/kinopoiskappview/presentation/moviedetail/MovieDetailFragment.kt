package com.example.kinopoiskappview.presentation.moviedetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.App
import com.example.kinopoiskappview.databinding.FragmentMovieDetailBinding
import com.example.kinopoiskappview.di.ViewModelFactory
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.navigation.MovieDetailNavigation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailBinding == null")

    private lateinit var movie: Movie

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
            .movieDetailComponentFactory()
            .create(movie)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        component.inject(this)
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
        binding.genresTextView.text = movie.genres.joinToString(SPACE)

        binding.reviewButton.setOnClickListener {
            (requireActivity() as MovieDetailNavigation).toReviewListScreen(movie)
        }
        binding.trailerButton.setOnClickListener {
            (requireActivity() as MovieDetailNavigation).toTrailerListScreen(movie)
        }

        binding.favouriteImageView.setOnClickListener {
            when (viewModel.uiState.value) {
                is CheckboxUiState.Added -> viewModel.removeMovie()
                is CheckboxUiState.NotAdded -> viewModel.addMovie()
            }
        }

        viewModel.getMovie()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collectLatest {
                    it.show(binding)
                }
            }
        }
    }

    private fun parseParams() {
        movie = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(MOVIE_PARAM, Movie::class.java)
        } else requireArguments().getSerializable(MOVIE_PARAM) as Movie)
            ?: throw IllegalArgumentException("Movie parameter is missing")
    }

    companion object {

        private const val MOVIE_PARAM = "MOVIE_PARAM"
        private const val SPACE = " "

        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MOVIE_PARAM, movie)
                }
            }
    }
}