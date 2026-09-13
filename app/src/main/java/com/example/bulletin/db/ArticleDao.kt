package com.example.bulletin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bulletin.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Article>)

    @Delete
    suspend fun delete(list: List<Article>)

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>>
}