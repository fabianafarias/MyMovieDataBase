package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.Movie
import com.fabianafarias.mymoviedatabase.network.ApiService
import org.koin.core.component.KoinComponent
import retrofit2.Response

class MovieRepositoryImpl( private val apiService: ApiService,
) : MovieRepository, KoinComponent {

    override suspend fun getMoviesNowPlaying(): MovieRepositoryResult<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviesUpComing(): MovieRepositoryResult<List<Movie>> {
        TODO("Not yet implemented")
    }

    private fun <T> proccessResponse(response: Response<T>) : MovieRepositoryResult<T> {
        return when (response.code()){
            in 200..299 -> {
                response.body()?.let {
                    MovieRepositoryResult.Success(it)
                }
            }
            401 -> MovieRepositoryResult.Error(MovieError.NOT_AUTHORIZED)
            404 -> MovieRepositoryResult.Error(MovieError.NOT_FOUND)
            else -> MovieRepositoryResult.Error(MovieError.GENERIC)
        }
    }
}