package com.example.example

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Articles")
data class Articles(

   var id: Int?=null,
   @SerializedName("source") var source: Source,
   @SerializedName("author") var author: String,
   @SerializedName("title") var title: String,
   @SerializedName("description") var description: String,
   @PrimaryKey()
   @SerializedName("url") var url: String,
   @SerializedName("urlToImage") var urlToImage: String,
   @SerializedName("publishedAt") var publishedAt: String,
   @SerializedName("content") var content: String
) : Serializable