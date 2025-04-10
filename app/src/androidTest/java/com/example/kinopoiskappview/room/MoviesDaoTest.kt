package com.example.kinopoiskappview.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kinopoiskappview.data.database.AppDatabase
import com.example.kinopoiskappview.data.database.MoviesDao
import com.example.kinopoiskappview.data.database.model.MovieDbModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var moviesDao: MoviesDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        moviesDao = database.moviesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun testInsertMovie() = runBlocking {
        val movie = MovieDbModel(
            id = 1, name = "movie", year = "2025", description = "some description",
            kpRating = 8.0, posterUrl = "123",
            genres = listOf("комедия", "боевик", "драма")
        )
        moviesDao.insertMovie(movie)

        assertEquals(movie, moviesDao.getFavouriteMovie(111).first())
    }

    @Test
    fun testDeleteMovie() = runBlocking {
        val movie = MovieDbModel(
            id = 1, name = "movie1", year = "2025", description = "some description",
            kpRating = 8.0, posterUrl = "1",
            genres = listOf("комедия", "боевик", "драма")
        )
        moviesDao.insertMovie(movie)
        moviesDao.deleteMovie(movie.id)
        assertNull(moviesDao.getFavouriteMovie(movie.id).firstOrNull())
    }

    @Test
    fun testGetFavouriteMovie() = runBlocking {
        val movie = MovieDbModel(
            id = 1, name = "movie1", year = "2025", description = "some description",
            kpRating = 8.0, posterUrl = "1",
            genres = listOf("комедия", "боевик", "драма")
        )
        moviesDao.insertMovie(movie)
        assertEquals(movie, moviesDao.getFavouriteMovie(1).first())
    }

    @Test
    fun testGetFavouriteMovies() = runBlocking {
        val movie1 = MovieDbModel(
            id = 1, name = "movie1", year = "2025", description = "some description",
            kpRating = 8.0, posterUrl = "1",
            genres = listOf("комедия", "боевик", "драма")
        )
        val movie2 = MovieDbModel(
            id = 2, name = "movie2", year = "2025", description = "some description",
            kpRating = 8.0, posterUrl = "2",
            genres = listOf("комедия", "боевик")
        )
        moviesDao.insertMovie(movie1)
        moviesDao.insertMovie(movie2)
        assertEquals(listOf(movie1, movie2), moviesDao.getFavouriteMovies().first())
    }
}