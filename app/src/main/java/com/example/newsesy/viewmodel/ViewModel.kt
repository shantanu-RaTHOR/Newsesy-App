package com.example.newsesy.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.Articles
import com.example.example.NewsModal
import com.example.newsesy.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(var context:Context): ViewModel()
{
   lateinit var res:Response<NewsModal>

    var mutableLiveData=MutableLiveData<Response<NewsModal>>()
    var savedNews= MutableLiveData<List<Articles>>()
    val searchNews: MutableLiveData<Response<NewsModal>> = MutableLiveData()
    var searchNewsPage = 1
    var repo:Repository= Repository(context)
    fun getNews(countryCode: String): MutableLiveData<Response<NewsModal>>
    {
        getBreakingNews(countryCode);
        return mutableLiveData
    }
    fun getBreakingNews(countryCode: String): Job = viewModelScope.launch(Dispatchers.IO){
        val res=repo.getBreakingNews(countryCode,1)
        mutableLiveData.postValue(res)
    }


    fun searchNews(searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        val res = repo.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(res)
    }


    fun getNewsCategoryWise(category: String): MutableLiveData<Response<NewsModal>>
    {
        getBreakingNewsCategoryWise(category)
        return mutableLiveData
    }
    fun getBreakingNewsCategoryWise(category: String): Job = viewModelScope.launch(Dispatchers.IO){
        val res=repo.getBreakingNewsCategoryWise(category)
        mutableLiveData.postValue(res)
    }


     fun upsert(article: Articles) = viewModelScope.launch(Dispatchers.IO) {
         repo.upsert(article)
    }

    fun getSavedNews()= viewModelScope.launch(Dispatchers.IO) {
        savedNews.postValue(repo.getSavedNews())
    }

    fun deleteArticle(article: Articles)=viewModelScope.launch(Dispatchers.IO) {
        repo.deleteArticle(article)
    }

}
