package com.fabianafarias.mymoviedatabase.di

import com.fabianafarias.mymoviedatabase.network.ApiService
import com.fabianafarias.mymoviedatabase.repository.MovieRepository
import com.fabianafarias.mymoviedatabase.repository.MovieRepositoryImpl
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
        get<Retrofit>().create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
}
