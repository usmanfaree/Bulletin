package com.example.bulletin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bulletin.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Article>)
    @Delete
    suspend fun delete(list: List<Article>)

    @Query("SELECT * FROM articles")
     fun getArticle(): LiveData<List<Article>>


    @Query("SELECT * FROM articles")
    suspend fun getArticlesOnce(): List<Article>


}