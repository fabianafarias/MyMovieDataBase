package com.fabianafarias.mymoviedatabase.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fabianafarias.mymoviedatabase.databinding.MovieItemsLayoutBinding
import com.fabianafarias.mymoviedatabase.ui.model.MovieListModel

class MovieListAdapter(private val movie: List<MovieListModel>) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
       val binding = MovieItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        movie[position]?.let{
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = movie.size

    class MoviesViewHolder(private val binding: MovieItemsLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListModel) {
            binding.title.text = movie.title
            binding.genre.text = movie.genre
            binding.releaseDate.text = movie.releaseDate
            binding.rating.text = movie.voteAverage
            val imageItem = binding.movieImage
            val url = "https://image.tmdb.org/t/p/w500"
            Glide.with(this.itemView).load(url).into(imageItem)
        }
    }

}