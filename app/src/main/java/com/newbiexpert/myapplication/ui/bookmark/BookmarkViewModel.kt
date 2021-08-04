package com.newbiexpert.myapplication.ui.bookmark

import androidx.lifecycle.ViewModel
import com.newbiexpert.myapplication.source.news.NewsRepository
import org.koin.dsl.module

val bookmarkViewModel = module {
    factory { BookmarkViewModel(get()) }
}

class BookmarkViewModel(
    val repository: NewsRepository
) : ViewModel() {

    val title = "Disimpan"

}