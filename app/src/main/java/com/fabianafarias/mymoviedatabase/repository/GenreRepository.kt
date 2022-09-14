package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.GenreModel

sealed class GenreRepositoryResult<out T>(){
    data class Success <T>(val genres: List<GenreModel>) : GenreRepositoryResult<T>()
    class Error <T>(val genreError: GenreError) : GenreRepositoryResult<T>()
}

interface GenreRepository {
    suspend fun getGenreMovies() : GenreRepositoryResult<List<GenreModel>>
}