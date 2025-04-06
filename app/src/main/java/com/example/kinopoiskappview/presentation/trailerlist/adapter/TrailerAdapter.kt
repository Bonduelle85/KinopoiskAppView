package com.example.kinopoiskappview.presentation.trailerlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kinopoiskappview.databinding.TrailerItemBinding
import com.example.kinopoiskappview.domain.model.Trailer

class TrailerAdapter(
    private val context: Context
) : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback()) {
    private var onTrailerClickListener: OnTrailerClickListener? = null

    fun setOnTrailerClickListener (onTrailerClickListener: OnTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TrailerItemBinding.inflate(layoutInflater, parent, false)
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailerItem = getItem(position)
        holder.binding.trailerTextView.text = trailerItem.trailerName
        holder.binding.root.setOnClickListener {
            onTrailerClickListener?.onTrailerClick(trailerItem)
        }
    }
}

fun interface OnTrailerClickListener {
    fun onTrailerClick(trailer: Trailer)
}