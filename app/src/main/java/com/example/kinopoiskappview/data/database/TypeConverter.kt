package com.example.kinopoiskappview.data.database

import androidx.room.TypeConverter

class TypeConverter {

    @TypeConverter
    fun fromGenresList(genres: List<String>?): String? {
        return genres?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toGenresList(genresString: String?): List<String>? {
        return genresString?.split(",")
    }
}