package com.example.newsesy.db

import android.content.Context
import androidx.room.*
import com.example.example.Articles

@TypeConverters(Convertors::class)
@Database (entities = [Articles::class],version = 1)
abstract  class ArticleDataBase : RoomDatabase()
{
  abstract fun getArticlesDao():ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_db.db"
            ).build()
    }
}