package com.devspacecinenow.list

import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.UnknownHostException

class MovieListRepositoryTest {

    private val local: MovieListLocalDataSource = mock()
    private val remote: MovieListRemoteDataSource = mock()

    private val underTest by lazy {
        MovieListRepository(
            local = local,
            remote = remote
        )
    }

    //NowPlaying
    @Test
    fun `Given no internet connection When getting now playing movies Then return local data`(){
        runTest {
            //Given
            val localList = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.NowPlaying.name
            ))
            whenever(remote.getNowPlaying()).thenReturn(Result.failure(UnknownHostException("No internet")))
            whenever(local.getNowPlayingMovies()).thenReturn(localList)
            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting now playing movies Then return remote result`(){
        runTest {
            //Given
            val remoteResult = Result.failure<List<Movie>>(UnknownHostException("No internet"))
            whenever(remote.getNowPlaying()).thenReturn(remoteResult)
            whenever(local.getNowPlayingMovies()).thenReturn(emptyList())
            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = remoteResult
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given remote success When getting now playing movies Then return local data and update local data`(){
        runTest {
            //Given
            val list = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.NowPlaying.name
            ))
            val remoteResult = Result.success(list)
            whenever(remote.getNowPlaying()).thenReturn(remoteResult)
            whenever(local.getNowPlayingMovies()).thenReturn(list)
            //When
            val result = underTest.getNowPlaying()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            verify(local).updateLocalItems(list)

        }
    }

    //TopRated
    @Test
    fun `Given no internet connection When getting top rated movies Then return local data`(){
        runTest {
            //Given
            val localList = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.TopRated.name
            ))
            whenever(remote.getTopRated()).thenReturn(Result.failure(UnknownHostException("No internet")))
            whenever(local.getTopRatedMovies()).thenReturn(localList)
            //When
            val result = underTest.getTopRated()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting top rated movies Then return remote result`(){
        runTest {
            //Given
            val remoteResult = Result.failure<List<Movie>>(UnknownHostException("No internet"))
            whenever(remote.getTopRated()).thenReturn(remoteResult)
            whenever(local.getTopRatedMovies()).thenReturn(emptyList())
            //When
            val result = underTest.getTopRated()

            //Then
            val expected = remoteResult
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given remote success When getting top rated movies Then return local data and update local data`(){
        runTest {
            //Given
            val list = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.TopRated.name
            ))
            val remoteResult = Result.success(list)
            whenever(remote.getTopRated()).thenReturn(remoteResult)
            whenever(local.getTopRatedMovies()).thenReturn(list)
            //When
            val result = underTest.getTopRated()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            verify(local).updateLocalItems(list)

        }
    }

    //Popular
    @Test
    fun `Given no internet connection When getting popular movies Then return local data`(){
        runTest {
            //Given
            val localList = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.Popular.name
            ))
            whenever(remote.getPopular()).thenReturn(Result.failure(UnknownHostException("No internet")))
            whenever(local.getPopularMovies()).thenReturn(localList)
            //When
            val result = underTest.getPopular()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting popular movies Then return remote result`(){
        runTest {
            //Given
            val remoteResult = Result.failure<List<Movie>>(UnknownHostException("No internet"))
            whenever(remote.getPopular()).thenReturn(remoteResult)
            whenever(local.getPopularMovies()).thenReturn(emptyList())
            //When
            val result = underTest.getPopular()

            //Then
            val expected = remoteResult
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given remote success When getting popular movies Then return local data and update local data`(){
        runTest {
            //Given
            val list = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.Popular.name
            ))
            val remoteResult = Result.success(list)
            whenever(remote.getPopular()).thenReturn(remoteResult)
            whenever(local.getPopularMovies()).thenReturn(list)
            //When
            val result = underTest.getPopular()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            verify(local).updateLocalItems(list)

        }
    }

    //Upcoming
    @Test
    fun `Given no internet connection When getting upcoming movies Then return local data`(){
        runTest {
            //Given
            val localList = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.Popular.name
            ))
            whenever(remote.getUpcoming()).thenReturn(Result.failure(UnknownHostException("No internet")))
            whenever(local.getUpcomingMovies()).thenReturn(localList)
            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = Result.success(localList)
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given no internet connection and no local data When getting upcoming movies Then return remote result`(){
        runTest {
            //Given
            val remoteResult = Result.failure<List<Movie>>(UnknownHostException("No internet"))
            whenever(remote.getUpcoming()).thenReturn(remoteResult)
            whenever(local.getUpcomingMovies()).thenReturn(emptyList())
            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = remoteResult
            assertEquals(expected, result)

        }
    }

    @Test
    fun `Given remote success When getting upcoming movies Then return local data and update local data`(){
        runTest {
            //Given
            val list = listOf(Movie(
                id = 1,
                title = "title1",
                overview = "overview1",
                image = "image1",
                category = MovieCategory.Upcoming.name
            ))
            val remoteResult = Result.success(list)
            whenever(remote.getUpcoming()).thenReturn(remoteResult)
            whenever(local.getUpcomingMovies()).thenReturn(list)
            //When
            val result = underTest.getUpcoming()

            //Then
            val expected = Result.success(list)
            assertEquals(expected, result)
            verify(local).updateLocalItems(list)

        }
    }

}