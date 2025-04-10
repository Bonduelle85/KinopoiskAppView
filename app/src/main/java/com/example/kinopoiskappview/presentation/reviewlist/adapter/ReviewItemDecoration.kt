package com.example.kinopoiskappview.presentation.reviewlist.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ReviewItemDecoration(
    private val top: Int,
    private val start: Int,
    private val end: Int,
    private val bottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = this@ReviewItemDecoration.top
            left = this@ReviewItemDecoration.start
            right = this@ReviewItemDecoration.end
            bottom = this@ReviewItemDecoration.bottom
        }
    }
}