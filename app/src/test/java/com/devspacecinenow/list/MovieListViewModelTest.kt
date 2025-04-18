package com.devspacecinenow.list

import app.cash.turbine.test
import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.devspacecinenow.list.presentation.ui.MovieUiData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieListViewModelTest {

    private val repository: MovieListRepository = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    private val underTest by lazy {
        MovieListViewModel(repository, testDispatcher)
    }

    //TopRated
    @Test
    fun `Given fresh viewmodel When collecting to topRated Then assert expected value`() {
        runTest {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))

            underTest.uiTopRated.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1",
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }
        }
    }

    //NowPLaying
    @Test
    fun `Given fresh viewmodel When collecting to nowPlaying Then assert expected value`() {
        runTest {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            whenever(repository.getNowPlaying()).thenReturn(Result.success(movies))

            underTest.uiNowPLaying.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1",
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }
        }
    }

    //Popular
    @Test
    fun `Given fresh viewmodel When collecting to Popular Then assert expected value`() {
        runTest {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            whenever(repository.getPopular()).thenReturn(Result.success(movies))

            underTest.uiPopular.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1",
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }
        }
    }

    //Upcoming
    @Test
    fun `Given fresh viewmodel When collecting to Upcoming Then assert expected value`() {
        runTest {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Upcoming.name
                )
            )
            whenever(repository.getUpcoming()).thenReturn(Result.success(movies))

            underTest.uiUpcoming.test {
                val expected = MovieListUiState(
                    list = listOf(
                        MovieUiData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1",
                        )
                    )
                )

                assertEquals(expected, awaitItem())
            }
        }
    }

    @Test
    fun `Given fresh viewmodel When collecting to topRated Then assert loading state`() {
        runTest {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))

            val result = underTest.uiTopRated.value

            val expected = MovieListUiState(
                isLoading = true
            )

            assertEquals(expected, result)

        }
    }
}