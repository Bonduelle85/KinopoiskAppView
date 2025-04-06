package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailerDto(
    @SerializedName("name") val trailerName : String,
    @SerializedName("url") val url : String,
)
