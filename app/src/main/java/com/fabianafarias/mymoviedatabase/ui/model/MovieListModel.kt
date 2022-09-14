package com.fabianafarias.mymoviedatabase.ui.model

data class MovieListModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val genre: String,
    val voteAverage: String
)