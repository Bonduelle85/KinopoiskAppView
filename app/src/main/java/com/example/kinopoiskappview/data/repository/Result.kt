package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.domain.model.Review

sealed class Result {
    data class Success(val data: List<Review>) : Result()
    data class Error(val error: Exception) : Result()
}
