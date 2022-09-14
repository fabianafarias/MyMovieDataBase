package com.fabianafarias.mymoviedatabase.network

import com.fabianafarias.mymoviedatabase.model.Movie
import retrofit2.Response
import retrofit2.http.GET

const val API_MOVIE_KEY = "7bf5ab511997f4df718abcd11f76777b"

interface ApiMovieService {

    @GET("movie/now_playing?api_key=$API_MOVIE_KEY&language=en-US&page=1")
    suspend fun getMoviesNowPlaying(): Response<List<Movie>>

    @GET("movie/upcoming?api_key=$API_MOVIE_KEY&language=en-US&page=1")
    suspend fun getMoviesUpcoming(): Response<List<Movie>>

}