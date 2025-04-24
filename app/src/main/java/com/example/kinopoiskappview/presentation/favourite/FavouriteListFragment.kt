package com.example.kinopoiskappview.presentation.favourite

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
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.databinding.FragmentFavouriteListBinding
import com.example.kinopoiskappview.di.ViewModelFactory
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.favourite.adapter.FavouriteMoviesAdapter
import com.example.kinopoiskappview.presentation.moviedetail.MovieDetailFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteListFragment : Fragment() {

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding: FragmentFavouriteListBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteListBinding == null")

    private lateinit var adapter: FavouriteMoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavouriteMoviesAdapter()
        binding.movieListRecyclerView.adapter = adapter

        adapter.setOnFavouriteMovieClickListener { movie: Movie ->
            // TODO navigation to movie detail screen
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, MovieDetailFragment.newInstance(movie))
                .addToBackStack(null)
                .commit()
        }

        observeViewModel()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collectLatest { uiState ->
                    uiState.showList(adapter)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteListFragment()
    }
}