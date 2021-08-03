package com.example.newsesy.network
import com.example.newsesy.util.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofitinstance
{

         val api= Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build().create<NetworkApi>()

//         val retrofit by lazy {
//            Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(BASE_URL)
//                    .build()
//        }
//        val api by lazy {
//            retrofit.create<NetworkApi>()
//        }

}