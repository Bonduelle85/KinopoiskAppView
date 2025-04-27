package com.example.kinopoiskappview.presentation.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kinopoiskappview.App
import com.example.kinopoiskappview.databinding.FragmentMovieListBinding
import com.example.kinopoiskappview.di.ViewModelFactory
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.navigation.MovieListNavigation
import com.example.kinopoiskappview.presentation.movielist.adapter.MovieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieListBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { MovieAdapter() }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRecyclerView.adapter = adapter
        observeViewModel()

        adapter.setOnReachEndListListener {
            viewModel.loadMovies()
        }

        binding.errorInclude.tryAgainButton.setOnClickListener {
            viewModel.loadMovies()
        }

        adapter.setOnMovieClickListener { movie: Movie ->
            (requireActivity() as MovieListNavigation).toMovieDetailScreen(movie)
        }

        binding.floatingActionButton.setOnClickListener {
            (requireActivity() as MovieListNavigation).toFavouriteListScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collectLatest {
                    it.showList(adapter)
                    it.show(binding)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}