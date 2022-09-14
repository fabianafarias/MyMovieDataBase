package com.fabianafarias.mymoviedatabase.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabianafarias.mymoviedatabase.R
import com.fabianafarias.mymoviedatabase.databinding.MovieItemsLayoutBinding
import com.fabianafarias.mymoviedatabase.model.MovieListModel
import com.squareup.picasso.Picasso

class MovieListAdapter(private val movies: List<MovieListModel>) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            MovieItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        val url = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Picasso.get().load(url).error(R.drawable.ic_launcher_background).into(holder.movieImage)
        holder.tvTitle.text = movie.title
        holder.tvGenre.text = movie.genres?.first()?.name
        holder.tvReleaseDate.text = movie.releaseDate
        holder.tvRating.text = movie.voteAverage.toString()
    }

    override fun getItemCount(): Int = movies.size

    inner class MoviesViewHolder(private val binding: MovieItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            val movieImage = binding.movieImage
            val tvTitle = binding.title
            val tvGenre = binding.genre
            val tvReleaseDate = binding.releaseDate
            val tvRating = binding.rating
    }
}




