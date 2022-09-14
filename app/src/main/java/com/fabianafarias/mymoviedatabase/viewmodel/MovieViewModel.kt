package com.fabianafarias.mymoviedatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianafarias.mymoviedatabase.model.GenreModel
import com.fabianafarias.mymoviedatabase.model.MovieModel
import com.fabianafarias.mymoviedatabase.repository.*
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository,
                     private val genreRepository: GenreRepository) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> get() = _movieList

    private val _movieError = MutableLiveData<MovieError>()
    val movieError: LiveData<MovieError> get() = _movieError

    private val _genreList = MutableLiveData<List<GenreModel>>()
    val genreList: LiveData<List<GenreModel>> get() = _genreList

    private val _genreError = MutableLiveData<GenreError>()
    val genreError: LiveData<GenreError> get() = _genreError

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            when (val result = movieRepository.getMoviesNowPlaying()) {
                is MovieRepositoryResult.Success -> {
                    result.movies.let { movies ->
                        _movieList.postValue(movies)
                    }

                }
                is MovieRepositoryResult.Error -> {
                    when(result.movieError.name){
                        "NOT_AUTHORIZED" -> {
                            _movieError.postValue(MovieError.NOT_AUTHORIZED)
                        }
                        else -> {
                            _movieError.postValue(MovieError.NOT_FOUND)
                        }
                    }
                }

            }
        }
    }

    fun getMoviesUpComing() {
        viewModelScope.launch {
            when (val result = movieRepository.getMoviesUpComing()) {
                is MovieRepositoryResult.Success -> {
                    result.movies.let { movies ->
                        _movieList.postValue(movies)

                    }
                }
                is MovieRepositoryResult.Error -> {
                    when(result.movieError.name){
                        "NOT_AUTHORIZED" -> {
                            _movieError.postValue(MovieError.NOT_AUTHORIZED)
                        }
                        else -> {
                            _movieError.postValue(MovieError.NOT_FOUND)
                        }
                    }
                }

            }
        }
    }

    fun getGenreMovies(){
        viewModelScope.launch {
            when(val result = genreRepository.getGenreMovies()){
                is GenreRepositoryResult.Success -> {
                    result.genres.let { genres ->
                        _genreList.postValue(genres)
                    }
                }
                is GenreRepositoryResult.Error -> {
                    when(result.genreError.name){
                        "NOT_AUTHORIZED" -> {
                            _genreError.postValue(GenreError.NOT_AUTHORIZED)
                        }
                        else -> {
                            _genreError.postValue(GenreError.NOT_FOUND)
                        }
                    }
                }
            }
        }
    }


}