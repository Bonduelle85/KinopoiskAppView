package com.example.kinopoiskappview.presentation

import com.example.kinopoiskappview.domain.GetFavouritesUseCase
import com.example.kinopoiskappview.domain.RemoveMovieUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.favourite.FavouriteListViewModel
import com.example.kinopoiskappview.presentation.favourite.FavouriteMoviesUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FavouriteListViewModelTest {

    private lateinit var viewModel: FavouriteListViewModel

    private val getFavouritesUseCase = mock<GetFavouritesUseCase>()
    private val removeMovieUseCase = mock<RemoveMovieUseCase>()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        createViewModel()
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        Mockito.reset(getFavouritesUseCase, removeMovieUseCase)
    }

    private fun createViewModel() {
        viewModel = FavouriteListViewModel(
            getFavouritesUseCase = getFavouritesUseCase,
            removeMovieUseCase = removeMovieUseCase
        )
    }

    @Test
    fun `initial state should be Initial`() = runTest {
        assertEquals(FavouriteMoviesUiState.Initial, viewModel.uiState.value)
    }

    @Test
    fun `should emit EmptyWithMessage when no favourites`() = runTest {
        // Arrange
        whenever(getFavouritesUseCase.invoke()).thenReturn(flowOf(emptyList()))

        // Act
        createViewModel()
        advanceUntilIdle()

        // Assert
        assertEquals(FavouriteMoviesUiState.EmptyWithMessage, viewModel.uiState.value)
        verify(getFavouritesUseCase, times(2)).invoke()
    }

    @Test
    fun `should emit Movies state when favourites exist`() = runTest {
        // Arrange
        val testMovies = listOf(
            Movie(
                id = 1L,
                name = "Name1",
                year = "1000",
                description = "Description1",
                kpRating = 1.0,
                posterUrl = "posterUrl1",
                genres = listOf("genre", "genre", "genre")
            ),
            Movie(
                id = 2L,
                name = "Name2",
                year = "2000",
                description = "Description2",
                kpRating = 2.0,
                posterUrl = "posterUrl2",
                genres = listOf("genre", "genre", "genre")
            )
        )
        whenever(getFavouritesUseCase.invoke()).thenReturn(flowOf(testMovies))

        // Act (init block already calls getFavouriteMovies)
        createViewModel()
        advanceUntilIdle()

        // Assert
        assertEquals(FavouriteMoviesUiState.Movies(testMovies), viewModel.uiState.value)
        verify(getFavouritesUseCase, times(2)).invoke()
    }

    @Test
    fun `should transition to empty state after removing last movie`() = runTest {
        // Arrange
        val initialMovies = listOf(
            Movie(
                id = 1L,
                name = "Name1",
                year = "1000",
                description = "Description1",
                kpRating = 1.0,
                posterUrl = "posterUrl1",
                genres = listOf("genre", "genre", "genre")
            )
        )
        whenever(getFavouritesUseCase.invoke())
            .thenReturn(flowOf(initialMovies))
            .thenReturn(flowOf(emptyList()))

        // Act
        createViewModel()
        viewModel.removeMovie(1L)
        viewModel.getFavouriteMovies()

        verify(removeMovieUseCase).invoke(1L)
        assertEquals(FavouriteMoviesUiState.EmptyWithMessage, viewModel.uiState.value)
    }
}