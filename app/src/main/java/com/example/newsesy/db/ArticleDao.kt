package com.example.newsesy.db
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.example.Articles
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(article: Articles): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Articles>

    @Delete
     fun deleteArticle(article: Articles)
}