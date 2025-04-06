package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class VideosDto(
    @SerializedName("trailers") val trailerDtos :List<TrailerDto>
)
