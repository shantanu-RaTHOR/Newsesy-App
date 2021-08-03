package com.example.newsesy.repository
import android.content.Context
import com.example.example.Articles
import com.example.newsesy.db.ArticleDataBase
import com.example.newsesy.network.Retrofitinstance
class Repository(var context: Context)
{
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        Retrofitinstance.api.getBreakingNews(countryCode)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        Retrofitinstance.api.searchForNews(searchQuery, pageNumber)

    var db=ArticleDataBase(context);
    suspend fun getBreakingNewsCategoryWise(category: String)=
        Retrofitinstance.api.getBreakingNewsCategoryWise("in",category,1)
    suspend fun upsert(article: Articles) = db.getArticlesDao().upsert(article)

    fun getSavedNews() = db.getArticlesDao().getAllArticles()

    suspend fun deleteArticle(article: Articles) = db.getArticlesDao().deleteArticle(article)


}