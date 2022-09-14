package com.fabianafarias.mymoviedatabase.model

import com.fabianafarias.mymoviedatabase.model.GenreModel
import com.fabianafarias.mymoviedatabase.model.MovieModel

class MovieListModel(movie: MovieModel, allGenres: List<GenreModel>){

    val id: Int?
    val voteAverage: Double?
    val title: String?
    val originalTitle: String?
    val popularity: Double?
    val posterPath: String?
    val backdropPath: String?
    val overview: String?
    val releaseDate: String?
    val genres: List<GenreModel>?

    init {
        id = movie.id
        voteAverage = movie.voteAverage
        title = movie.title
        originalTitle = movie.originalTitle
        popularity = movie.popularity
        posterPath = movie.posterPath
        backdropPath = movie.backdropPath
        overview = movie.overview
        releaseDate = movie.releaseDate

        val arrayAux = arrayListOf<GenreModel>()
        movie.genres?.forEach {
            getGenre(it, allGenres)?.let { arrayAux.add(it) }
        }

        genres = arrayAux.toList()

    }

    private fun getGenre(genreId : Int?, allGenres : List<GenreModel>) : GenreModel?{
        return allGenres.filter {it?.id?.equals(genreId)?:false}.first()
    }
}
