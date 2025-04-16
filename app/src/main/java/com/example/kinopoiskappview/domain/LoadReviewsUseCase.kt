package com.example.kinopoiskappview.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.kinopoiskappview.domain.model.Result
import com.example.kinopoiskappview.domain.model.Review

class LoadReviewsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(id: Long): Flow<Result<List<Review>>> = repository.loadReviews(id)
}
