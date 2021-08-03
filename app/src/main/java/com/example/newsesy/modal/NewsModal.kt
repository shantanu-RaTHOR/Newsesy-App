package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewsModal (
   @SerializedName("status") var status : String,
   @SerializedName("totalResults") var totalResults : Int,
   @SerializedName("articles") var articles : List<Articles>
) : Serializable