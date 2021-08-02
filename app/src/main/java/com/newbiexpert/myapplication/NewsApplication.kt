package com.newbiexpert.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.newbiexpert.myapplication.source.network.networkModule
import com.newbiexpert.myapplication.source.news.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("Run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)
            modules(
                networkModule,
                repositoryModule
            )
        }
    }
}