package com.newbiexpert.myapplication.ui.home

import androidx.lifecycle.ViewModel
import com.newbiexpert.myapplication.source.news.NewsRepository
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: NewsRepository
) : ViewModel() {

    val title = "Berita"
}