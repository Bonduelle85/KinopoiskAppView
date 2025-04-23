package com.example.kinopoiskappview.presentation.reviewlist

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
import com.example.kinopoiskappview.databinding.FragmentReviewListBinding
import com.example.kinopoiskappview.di.ViewModelFactory
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.reviewlist.adapter.ReviewAdapter
import com.example.kinopoiskappview.presentation.reviewlist.adapter.ReviewItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ReviewListFragment : Fragment() {

    private var _binding: FragmentReviewListBinding? = null
    private val binding: FragmentReviewListBinding
        get() = _binding ?: throw RuntimeException("FragmentReviewListBinding == null")

    private lateinit var adapter: ReviewAdapter
    private lateinit var movie: Movie

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
            .reviewListComponentFactory()
            .create(movie)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ReviewListViewModel::class.java]
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
        _binding = FragmentReviewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ReviewAdapter()
        val recyclerView = binding.reviewListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ReviewItemDecoration(16, 32, 32, 16))

        binding.titleTextView.text = getString(R.string.review_list_title, movie.name)
        binding.errorInclude.tryAgainButton.setOnClickListener {
            viewModel.loadReviews()
        }

        viewModel.loadReviews()

        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collectLatest {uiState ->
                    uiState.show(binding)
                    uiState.showList(adapter)
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
            return ReviewListFragment().apply {
                arguments = bundleOf(MOVIE_PARAM to movie)
            }
        }
    }
}