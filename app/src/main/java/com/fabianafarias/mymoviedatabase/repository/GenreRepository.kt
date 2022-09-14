package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.Genre

sealed class GenreRepositoryResult<out T>(){
    data class Success <T>(val genres: List<Genre>) : GenreRepositoryResult<T>()
    class Error <T>(val genreError: GenreError) : GenreRepositoryResult<T>()
}

interface GenreRepository {
    suspend fun getGenreMovies() : GenreRepositoryResult<List<Genre>>
}