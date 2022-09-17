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

    lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val comingSoonBtn = binding.btnComingSoon
       val nowPlayingBtn = binding.btnNowPlaying

        observeViewModel()
        getNowPlaying()
        nowPlayingBtn.isSelected = true

       comingSoonBtn.setOnClickListener{
            //clear state
            comingSoonBtn.isSelected = false
            nowPlayingBtn.isSelected = false
            // change state
            comingSoonBtn.isSelected = true
            nowPlayingBtn.isPressed = false
            getUpComing()
        }

        nowPlayingBtn.setOnClickListener{
            //clear state
            comingSoonBtn.isSelected = false
            nowPlayingBtn.isSelected = false
            // change state
            nowPlayingBtn.isSelected = true
            comingSoonBtn.isPressed = false
            getNowPlaying()
        }

    }

    private fun getNowPlaying(){
        lifecycleScope.launch {
            movieViewModel.getMoviesNowPlaying()
        }
    }

    private fun getUpComing(){
        lifecycleScope.launch {
            movieViewModel.getMoviesUpComing()
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