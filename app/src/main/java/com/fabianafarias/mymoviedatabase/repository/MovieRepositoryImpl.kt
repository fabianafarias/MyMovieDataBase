package com.fabianafarias.mymoviedatabase.repository

import com.fabianafarias.mymoviedatabase.model.MovieListModel
import com.fabianafarias.mymoviedatabase.model.MovieResponse
import com.fabianafarias.mymoviedatabase.network.ApiMovieService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieRepositoryImpl() : MovieRepository, KoinComponent {

    private val service: ApiMovieService by inject()

    override suspend fun getMoviesNowPlaying(): MovieRepositoryResult {
        val movies = service.getMoviesNowPlaying()
        if (movies.isSuccessful) {
            movies.body()?.let {
                return getMoviesViewModelList(it)
            }
        }
        return MovieRepositoryResult.Error()
    }

    override suspend fun getMoviesUpComing(): MovieRepositoryResult {
        val movies = service.getMoviesUpcoming()
        if (movies.isSuccessful) {
            movies.body()?.let {
                return getMoviesViewModelList(it)
            }
        }
        return MovieRepositoryResult.Error()
    }

    suspend fun getMoviesViewModelList(movieResponse: MovieResponse) : MovieRepositoryResult {
        val genres = service.getGenreMovies()
        if (genres.isSuccessful) {
            val movieListModelArray = arrayListOf<MovieListModel>()
            genres.body()?.let { genreResponse ->
                movieResponse.results.forEach {
                    val movieListModel = MovieListModel(it, genreResponse.genres)
                    movieListModelArray.add(movieListModel)
                }
                return MovieRepositoryResult.Success(movieListModelArray.toList())
            }
        }
        return MovieRepositoryResult.Error()
    }



}