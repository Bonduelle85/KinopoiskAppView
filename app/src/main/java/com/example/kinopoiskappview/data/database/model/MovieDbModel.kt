package com.example.kinopoiskappview.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieDbModel (
    @PrimaryKey
    @ColumnInfo("id") val id: Long,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("year") val year: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("kp_rating") val kpRating: Double,
    @ColumnInfo("posterUrl") val posterUrl: String,
    @ColumnInfo("genres") val genres: List<String>,
)