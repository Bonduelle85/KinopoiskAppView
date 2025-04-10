package com.example.kinopoiskappview.domain.model

import com.example.kinopoiskappview.data.ReviewType

data class Review(
    val author : String,
    val title : String,
    val type : ReviewType,
    val review : String,
    val likes : Int,
    val dislikes : Int,
    val createdAt : String,
)