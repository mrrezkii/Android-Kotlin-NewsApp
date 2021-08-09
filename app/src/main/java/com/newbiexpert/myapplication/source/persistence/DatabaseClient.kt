package com.newbiexpert.myapplication.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newbiexpert.myapplication.source.news.ArticleModel
import com.newbiexpert.myapplication.source.news.NewsDao


@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)

abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDatabase: NewsDao
}