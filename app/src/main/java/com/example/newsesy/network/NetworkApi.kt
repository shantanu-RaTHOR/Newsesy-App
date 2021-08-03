package com.example.newsesy.network
import com.example.example.NewsModal
import com.example.newsesy.util.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface NetworkApi
{
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsModal>


    @GET("v2/top-headlines")
    suspend fun getBreakingNewsCategoryWise(
        @Query("country")
        countryCode: String,
        @Query("category")
        category:String,
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsModal>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsModal>


}