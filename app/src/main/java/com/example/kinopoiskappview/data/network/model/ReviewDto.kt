package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("type") val type : String,
    @SerializedName("review") val review : String,
    @SerializedName("reviewLikes") val likes : Int,
    @SerializedName("reviewDislikes") val dislikes : Int,
    @SerializedName("createdAt") val createdAt : String?,
)
