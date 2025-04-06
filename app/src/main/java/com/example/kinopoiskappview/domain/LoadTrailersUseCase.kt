package com.example.kinopoiskappview.domain

import javax.inject.Inject

class LoadTrailersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(id: Long) = repository.loadTrailers(id)
}