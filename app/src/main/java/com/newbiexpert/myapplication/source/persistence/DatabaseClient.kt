package com.newbiexpert.myapplication.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.newbiexpert.myapplication.source.news.ArticleModel
import com.newbiexpert.myapplication.source.news.NewsDao
import com.newbiexpert.myapplication.util.SourceConverter


@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDatabase: NewsDao
}