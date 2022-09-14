package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.MovieListModel

sealed class MovieRepositoryResult {
    data class Success (val movies: List<MovieListModel>) : MovieRepositoryResult()
    class Error : MovieRepositoryResult()
}

interface MovieRepository {
    suspend fun getMoviesNowPlaying() : MovieRepositoryResult
    suspend fun getMoviesUpComing() : MovieRepositoryResult
}