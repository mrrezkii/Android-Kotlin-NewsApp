package com.newbiexpert.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.newbiexpert.myapplication.source.network.networkModule
import com.newbiexpert.myapplication.source.news.repositoryModule
import com.newbiexpert.myapplication.source.persistence.databaseModule
import com.newbiexpert.myapplication.ui.bookmark.bookmarkModule
import com.newbiexpert.myapplication.ui.bookmark.bookmarkViewModel
import com.newbiexpert.myapplication.ui.detail.detailModule
import com.newbiexpert.myapplication.ui.detail.detailViewModel
import com.newbiexpert.myapplication.ui.home.homeModule
import com.newbiexpert.myapplication.ui.home.homeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("Run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NewsApplication)
            modules(
                networkModule,
                repositoryModule,
                homeViewModel,
                homeModule,
                bookmarkViewModel,
                bookmarkModule,
                databaseModule,
                detailViewModel,
                detailModule
            )
        }
    }
}