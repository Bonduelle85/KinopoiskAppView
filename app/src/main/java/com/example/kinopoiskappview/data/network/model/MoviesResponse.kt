package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("docs") val movies : List<MovieDto>
)
