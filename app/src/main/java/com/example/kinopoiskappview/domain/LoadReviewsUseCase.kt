package com.example.kinopoiskappview.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.kinopoiskappview.data.repository.Result

class LoadReviewsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(id: Long): Flow<Result> = repository.loadReviews(id)
}
