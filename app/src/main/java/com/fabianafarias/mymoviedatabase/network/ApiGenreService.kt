package com.fabianafarias.mymoviedatabase.network

import com.fabianafarias.mymoviedatabase.model.GenreModel
import retrofit2.Response
import retrofit2.http.GET

const val API_GENRE_KEY = "7bf5ab511997f4df718abcd11f76777b"

interface ApiGenreService {

    @GET("genre/movie/list?api_key=$API_GENRE_KEY&language=en-US&page=1")
    suspend fun getGenreMovies(): Response<List<GenreModel>>
}