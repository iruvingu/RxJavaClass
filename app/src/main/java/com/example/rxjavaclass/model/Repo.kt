package com.example.rxjavaclass.model

import com.google.gson.annotations.SerializedName

data class Repo(val name: String = ""
                ,@SerializedName("description") val description: String?
                ,@SerializedName("language") val language: String = ""
                ,@SerializedName("stargazers_count") val starCount: Int){

}