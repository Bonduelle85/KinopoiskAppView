package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("docs") val reviews : List<ReviewDto>
)
