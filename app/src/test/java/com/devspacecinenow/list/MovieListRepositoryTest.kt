package com.devspacecinenow.list

import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.MovieListRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.UnknownHostException

class MovieListRepositoryTest {

    private val local = FakeMovieListLocalDataSource()
    private val remote = FakeMovieListRemoteDataSource()

    private val underTest by lazy {
        MovieListRepository(
            local = local,
            remote = remote
        )
    }

    //NowPlaying
    @Test
    fun `Given no internet connection When getting now playing movies Then return local data`() {
        runTest {
            //Given
            val localList = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            remote.shouldReturnError = true
            local.nowPlaying = localList

            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }


    @Test
    fun `Given no internet connection and no local data When getting now playing movies Then return remote result`() {
        runTest {
            //Given
            val exception = UnknownHostException("No internet")
            remote.shouldReturnError = true
            local.nowPlaying = emptyList()

            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = Result.failure<List<Movie>>(exception)
            assertEquals(expected.exceptionOrNull()!!::class, result.exceptionOrNull()!!::class)
            assertEquals(expected.exceptionOrNull()?.message, result.exceptionOrNull()?.message)

        }
    }

    @Test
    fun `Given remote success When getting now playing movies Then update local data`() {
        runTest {
            //Given
            val list = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            remote.shouldReturnError = false
            remote.nowPlaying = list
            local.nowPlaying = list
            local.updateItems = list

            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            assertEquals(local.updateItems, list)

        }
    }

    //TopRated
    @Test
    fun `Given no internet connection When getting top rated movies Then return local data`() {
        runTest {
            //Given
            val localList = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            remote.shouldReturnError = true
            local.topRated = localList

            //When
            val result = underTest.getTopRated()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting top rated movies Then return remote result`() {
        runTest {
            //Given
            val exception = UnknownHostException("No internet")
            remote.shouldReturnError = true
            local.topRated = emptyList()
            //When
            val result = underTest.getTopRated()

            //Then
            val expected = Result.failure<List<Movie>>(exception)
            assertEquals(expected.exceptionOrNull()!!::class, result.exceptionOrNull()!!::class)
            assertEquals(expected.exceptionOrNull()?.message, result.exceptionOrNull()?.message)

        }
    }

    @Test
    fun `Given remote success When getting top rated movies Then update local data`() {
        runTest {
            //Given
            val list = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            remote.shouldReturnError = false
            remote.topRated = list
            local.topRated = list
            local.updateItems = list

            //When
            val result = underTest.getTopRated()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            assertEquals(local.updateItems, list)

        }
    }

    //Popular
    @Test
    fun `Given no internet connection When getting popular movies Then return local data`() {
        runTest {
            //Given
            val localList = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            remote.shouldReturnError = true
            local.popular = localList

            //When
            val result = underTest.getPopular()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting popular movies Then return remote result`() {
        runTest {
            //Given
            val exception = UnknownHostException("No internet")
            remote.shouldReturnError = true
            local.popular = emptyList()

            //When
            val result = underTest.getPopular()

            //Then
            val expected = Result.failure<List<Movie>>(exception)
            assertEquals(expected.exceptionOrNull()!!::class, result.exceptionOrNull()!!::class)
            assertEquals(expected.exceptionOrNull()?.message, result.exceptionOrNull()?.message)

        }
    }

    @Test
    fun `Given remote success When getting popular movies Then update local data`() {
        runTest {
            //Given
            val list = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            remote.shouldReturnError = false
            remote.popular = list
            local.popular = list
            local.updateItems = list

            //When
            val result = underTest.getPopular()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            assertEquals(local.updateItems, list)

        }
    }

    //Upcoming
    @Test
    fun `Given no internet connection When getting upcoming movies Then return local data`() {
        runTest {
            //Given
            val localList = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Popular.name
                )
            )
            remote.shouldReturnError = true
            local.upcoming = localList

            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting upcoming movies Then return remote result`() {
        runTest {
            //Given
            val exception = UnknownHostException("No internet")
            remote.shouldReturnError = true
            local.upcoming = emptyList()

            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = Result.failure<List<Movie>>(exception)
            assertEquals(expected.exceptionOrNull()!!::class, result.exceptionOrNull()!!::class)
            assertEquals(expected.exceptionOrNull()?.message, result.exceptionOrNull()?.message)

        }
    }

    @Test
    fun `Given remote success When getting upcoming movies Then update local data`() {
        runTest {
            //Given
            val list = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.Upcoming.name
                )
            )
            remote.shouldReturnError = false
            remote.upcoming = list
            local.upcoming = list
            local.updateItems = list

            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            assertEquals(local.updateItems, list)

        }
    }

}