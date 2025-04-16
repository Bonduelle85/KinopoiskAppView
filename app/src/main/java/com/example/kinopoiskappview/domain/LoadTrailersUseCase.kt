package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Result
import com.example.kinopoiskappview.domain.model.Trailer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadTrailersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(id: Long): Flow<Result<List<Trailer>>> = repository.loadTrailers(id)
}