package com.fabianafarias.mymoviedatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianafarias.mymoviedatabase.model.MovieListModel
import com.fabianafarias.mymoviedatabase.repository.MovieRepository
import com.fabianafarias.mymoviedatabase.repository.MovieRepositoryResult
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieViewModel() : ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    private val _movieList = MutableLiveData<List<MovieListModel>>()
    val movieList: LiveData<List<MovieListModel>> get() = _movieList

    private val _movieError = MutableLiveData<Boolean>()
    val movieError: LiveData<Boolean> get() = _movieError

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            when (val result = movieRepository.getMoviesNowPlaying()) {
                is MovieRepositoryResult.Success -> _movieList.postValue(result.movies)
                else -> {
                    _movieError.postValue(true)
                }
            }
        }
    }

    fun getMoviesUpComing() {
        viewModelScope.launch {
            when (val result = movieRepository.getMoviesUpComing()) {
                is MovieRepositoryResult.Success -> _movieList.postValue(result.movies)
                else -> {
                    _movieError.postValue(true)
                }
            }
        }
    }

}