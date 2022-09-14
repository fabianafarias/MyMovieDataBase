package com.fabianafarias.mymoviedatabase.di

import com.fabianafarias.mymoviedatabase.network.ApiGenreService
import com.fabianafarias.mymoviedatabase.network.ApiMovieService
import com.fabianafarias.mymoviedatabase.repository.GenreRepository
import com.fabianafarias.mymoviedatabase.repository.GenreRepositoryImpl
import com.fabianafarias.mymoviedatabase.repository.MovieRepository
import com.fabianafarias.mymoviedatabase.repository.MovieRepositoryImpl
import com.fabianafarias.mymoviedatabase.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

val retrofitModule = module {
    single<Retrofit>{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}

val serviceModule = module {
    single {
        get<Retrofit>().create(ApiMovieService::class.java)
    }
    single {
        get<Retrofit>().create(ApiGenreService::class.java)
    }
}

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
    single<GenreRepository> {
        GenreRepositoryImpl(get())
    }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}

