package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailersRootResponseDto (
    @SerializedName("videos") val videosDto : VideosDto
)
