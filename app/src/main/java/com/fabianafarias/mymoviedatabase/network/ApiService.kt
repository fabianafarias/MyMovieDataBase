package com.fabianafarias.mymoviedatabase.network

import com.fabianafarias.mymoviedatabase.model.GenreResponse
import com.fabianafarias.mymoviedatabase.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

const val API_KEY = "7bf5ab511997f4df718abcd11f76777b"

interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMoviesNowPlaying(): Response<MovieResponse>

    @GET("movie/upcoming?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMoviesUpcoming(): Response<MovieResponse>

    @GET("genre/movie/list?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getGenreMovies(): Response<GenreResponse>
}