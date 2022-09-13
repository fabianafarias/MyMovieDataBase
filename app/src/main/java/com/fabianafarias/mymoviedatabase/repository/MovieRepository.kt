package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.Movie

sealed class MovieRepositoryResult<out T>(){
    data class Success <T> (val movies: List<Movie>) : MovieRepositoryResult<T>()
    object Error <T>(val movieError: MovieError) : MovieRepositoryResult()
}

interface MovieRepository {

    suspend fun getMoviesNowPlaying() : MovieRepositoryResult<List<Movie>>
    suspend fun getMoviesUpComing() : MovieRepositoryResult<List<Movie>>
}