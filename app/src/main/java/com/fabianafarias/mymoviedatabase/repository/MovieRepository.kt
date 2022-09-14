package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.MovieModel

sealed class MovieRepositoryResult<out T>(){
    data class Success <T>(val movies: List<MovieModel>) : MovieRepositoryResult<T>()
    class Error <T>(val movieError: MovieError) : MovieRepositoryResult<T>()
}

interface MovieRepository {
    suspend fun getMoviesNowPlaying() : MovieRepositoryResult<List<MovieModel>>
    suspend fun getMoviesUpComing() : MovieRepositoryResult<List<MovieModel>>
}