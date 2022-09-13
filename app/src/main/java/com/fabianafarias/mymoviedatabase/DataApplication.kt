package com.fabianafarias.mymoviedatabase

import android.app.Application
import com.fabianafarias.mymoviedatabase.di.repositoryModule
import com.fabianafarias.mymoviedatabase.di.retrofitModule
import com.fabianafarias.mymoviedatabase.di.serviceModule
import com.fabianafarias.mymoviedatabase.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DataApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DataApplication)
            modules(
                listOf(
                    retrofitModule,
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}