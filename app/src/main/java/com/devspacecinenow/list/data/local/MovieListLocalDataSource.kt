package com.devspacecinenow.list.data.local

import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.local.MovieDao
import com.devspacecinenow.common.data.local.MovieEntity
import com.devspacecinenow.common.data.model.Movie

class MovieListLocalDataSource(
    private val dao: MovieDao
) {

    suspend fun getNowPlayingMovies(): List<Movie> {
        return getMoviesByCategories(MovieCategory.NowPlaying)
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return getMoviesByCategories(MovieCategory.TopRated)
    }

    suspend fun getPopularMovies(): List<Movie> {
        return getMoviesByCategories(MovieCategory.Popular)
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return getMoviesByCategories(MovieCategory.Upcoming)
    }

    suspend fun updateLocalItems(movies: List<Movie>){
        val entities = movies.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                category = it.category,
                image = it.image
            )
        }
        dao.insterAll(entities)
    }

    private suspend fun getMoviesByCategories(movieCategory: MovieCategory): List<Movie> {
        val entities = dao.getMoviesByCategory(movieCategory.name)

        return entities.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                image = it.image,
                category = it.category
            )
        }
    }

}