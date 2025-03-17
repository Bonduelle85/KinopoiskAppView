package com.example.kinopoiskappview.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class MovieDbModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Long = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("year") val year: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("kp_rating") val kpRating: Double,
    @ColumnInfo("imdb_rating") val imdbRating: Double,
    @ColumnInfo("posterUrl") val posterUrl: String,
    @ColumnInfo("genres") val genres: List<String>,
)