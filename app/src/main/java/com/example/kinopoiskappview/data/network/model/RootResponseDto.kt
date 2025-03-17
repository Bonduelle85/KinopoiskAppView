package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class RootResponseDto (
    @SerializedName("docs") val movies : List<MovieDto>
)
