package com.example.kinopoiskappview.presentation.trailerlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kinopoiskappview.App
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.di.ViewModelFactory
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.trailerlist.adapter.TrailerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.net.toUri
import com.example.kinopoiskappview.databinding.FragmentTrailerListBinding


class TrailerListFragment : Fragment() {

    private var _binding: FragmentTrailerListBinding? = null
    private val binding: FragmentTrailerListBinding
        get() = _binding ?: throw RuntimeException("FragmentTrailerListBinding == null")

    private lateinit var adapter: TrailerAdapter
    private lateinit var movie: Movie

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
            .trailerListComponentComponentFactory()
            .create(movie)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TrailerListViewModel::class.java]
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
        _binding = FragmentTrailerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TrailerAdapter()
        binding.trailerListRecyclerView.adapter = adapter

        adapter.setOnTrailerClickListener {trailer ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(trailer.url.toUri())
            startActivity(intent)
        }

        val formattedTittle = getString(R.string.trailers_list_title, movie.name)
        binding.titleTextView.text = formattedTittle
        viewModel.loadTrailers()

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
                    adapter.submitList(it)
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

        private const val MOVIE_PARAM = "movie"

        @JvmStatic
        fun newInstance(movie: Movie): Fragment {
            return TrailerListFragment().apply {
                arguments = bundleOf(MOVIE_PARAM to movie)
            }
        }
    }
}