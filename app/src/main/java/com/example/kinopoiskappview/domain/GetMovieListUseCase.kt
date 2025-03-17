package com.example.kinopoiskappview.domain

import androidx.lifecycle.LiveData

class GetMovieListUseCase() {

    val repository = Repository()
    operator fun invoke(): LiveData<List<CoinInfo>> = repository.getCoinInfoList()
}