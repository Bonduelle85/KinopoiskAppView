package com.example.kinopoiskappview.presentation.favourite

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskappview.presentation.favourite.adapter.FavouriteMoviesAdapter

class FavoriteMovieSwipeCallback(
    private val adapter: FavouriteMoviesAdapter,
    private val viewModel: FavouriteListViewModel,
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val movie = adapter.currentList[viewHolder.adapterPosition]
        viewModel.removeMovie(movie.id)
    }
}