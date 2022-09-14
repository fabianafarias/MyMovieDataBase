package com.fabianafarias.mymoviedatabase.ui.movies

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fabianafarias.mymoviedatabase.databinding.ActivityMainBinding
import com.fabianafarias.mymoviedatabase.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()

        getNowPlaying()

        binding.btnNowPlaying.setOnClickListener {
            getNowPlaying()
        }

        binding.btnComingSoon.setOnClickListener {
            getUpComing()
        }

    }

    private fun getNowPlaying(){
        lifecycleScope.launch {
            movieViewModel.getMoviesNowPlaying()
        }
    }

    private fun getUpComing(){
        lifecycleScope.launch {
            movieViewModel.getMoviesNowPlaying()
        }
    }

    private fun observeViewModel(){
        movieViewModel.movieList.observe(this) {
            val adapter = MovieListAdapter(it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.visibility = VISIBLE
        }
    }

}