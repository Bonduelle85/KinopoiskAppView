package com.example.kinopoiskappview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinopoiskappview.data.database.model.MovieDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favourite_movies ORDER BY name")
    fun getFavouriteMovies(): Flow<List<MovieDbModel>>

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    fun getFavouriteMovie(id: Long): Flow<MovieDbModel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MovieDbModel)

    @Query("DELETE FROM favourite_movies WHERE id = :id")
    suspend fun deleteMovie(id: Long)
}