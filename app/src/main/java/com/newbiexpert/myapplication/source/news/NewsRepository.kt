package com.newbiexpert.myapplication.source.news

import com.newbiexpert.myapplication.BuildConfig
import com.newbiexpert.myapplication.source.network.ApiClient
import org.koin.dsl.module


val repositoryModule = module {
    factory { NewsRepository(get(), get()) }
}

class NewsRepository(
    private val api: ApiClient,
    val db: NewsDao
) {

    suspend fun fetch(category: String, query: String, page: Int): NewsModel {
        return api.fetchNews(
            BuildConfig.API_KEY,
            "id",
            category,
            query,
            page
        )
    }

    suspend fun find(articleModel: ArticleModel) = db.find(articleModel.publishedAt)

    suspend fun save(articleModel: ArticleModel) {
        db.save(articleModel)
    }

    suspend fun remove(articleModel: ArticleModel) {
        db.remove(articleModel)
    }

}