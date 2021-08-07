package com.newbiexpert.myapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newbiexpert.myapplication.source.news.CategoryModel
import com.newbiexpert.myapplication.source.news.NewsModel
import com.newbiexpert.myapplication.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: NewsRepository
) : ViewModel() {

    val title = "Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val news by lazy { MutableLiveData<NewsModel>() }

    init {
        category.value = ""
        message.value = null
        fetch()
    }

    fun fetch() {
        try {
            viewModelScope.launch {
                val response = repository.fetch(
                    "",
                    "",
                    1
                )
                news.value = response
            }
        } catch (e: Exception) {
            message.value = "Terjadi kesalahan"
        }
    }

    val categories = listOf<CategoryModel>(
        CategoryModel("", "Berita Utama"),
        CategoryModel("business", "Bisnis"),
        CategoryModel("entertainment", "Hiburan"),
        CategoryModel("general", "Umum"),
        CategoryModel("health", "Kesehatan"),
        CategoryModel("science", "Sains"),
        CategoryModel("sports", "Olah Raga"),
        CategoryModel("technology", "Teknologi"),
    )
}