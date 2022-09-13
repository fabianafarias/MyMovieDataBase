package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.Movie
import com.fabianafarias.mymoviedatabase.network.ApiService
import retrofit2.Response

class MovieRepositoryImpl( private val apiService: ApiService ) : MovieRepository {

    private fun <T> proccessResponse(response: Response<List<Movie>>) : MovieRepositoryResult<T> {
        return when (response.code()){
            in 200..299 -> {
                response.body()?.let {
                    MovieRepositoryResult.Success(it)
                } ?: MovieRepositoryResult.Error(MovieError.GENERIC)
            }
            404 -> MovieRepositoryResult.Error(MovieError.NOT_FOUND)
            401 -> MovieRepositoryResult.Error(MovieError.NOT_AUTHORIZED)
            else -> MovieRepositoryResult.Error(MovieError.GENERIC)
        }
    }

    override suspend fun getMoviesNowPlaying(): MovieRepositoryResult<List<Movie>> {
        return proccessResponse(apiService.getMoviesNowPlaying())
    }

    override suspend fun getMoviesUpComing(): MovieRepositoryResult<List<Movie>> {
        return proccessResponse(apiService.getMoviesUpcoming())
    }


}