package com.fabianafarias.mymoviedatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianafarias.mymoviedatabase.model.Movie
import com.fabianafarias.mymoviedatabase.repository.MovieError
import com.fabianafarias.mymoviedatabase.repository.MovieRepository
import com.fabianafarias.mymoviedatabase.repository.MovieRepositoryResult
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    private val _movieError = MutableLiveData<MovieError>()
    val movieError: LiveData<MovieError> get() = _movieError

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            when (val result = movieRepository.getMoviesNowPlaying()) {
                is MovieRepositoryResult.Success -> {
                    result.movies.let {
                        _movieList.postValue(it)
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

}