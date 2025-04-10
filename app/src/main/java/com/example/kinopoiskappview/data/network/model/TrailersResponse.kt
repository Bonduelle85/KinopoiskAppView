package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailersResponse (
    @SerializedName("videos") val videosDto : VideosDto
)
