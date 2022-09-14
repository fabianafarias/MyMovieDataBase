package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.GenreModel
import com.fabianafarias.mymoviedatabase.network.ApiGenreService
import retrofit2.Response

class GenreRepositoryImpl(
    private val apiGenreService: ApiGenreService
    ) : GenreRepository {

    override suspend fun getGenreMovies(): GenreRepositoryResult<List<GenreModel>> {
        return proccessResponse(apiGenreService.getGenreMovies())
    }

    private fun <T> proccessResponse(response: Response<List<GenreModel>>
    ) : GenreRepositoryResult<T> {
        return when (response.code()){
            in 200..299 -> {
                response.body()?.let {
                    GenreRepositoryResult.Success(it)
                } ?: GenreRepositoryResult.Error(GenreError.GENERIC)
            }
            404 -> GenreRepositoryResult.Error(GenreError.NOT_FOUND)
            401 -> GenreRepositoryResult.Error(GenreError.NOT_AUTHORIZED)
            else -> GenreRepositoryResult.Error(GenreError.GENERIC)
        }
    }

}