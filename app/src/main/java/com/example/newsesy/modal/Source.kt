package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Source (
   @SerializedName("id") var id : String,
   @SerializedName("name") var name : String

) : Serializable