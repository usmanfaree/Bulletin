package com.example.bulletin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bulletin.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao


}