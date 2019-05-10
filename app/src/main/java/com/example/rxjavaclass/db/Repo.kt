package com.example.rxjavaclass.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repo")
data class Repo(@PrimaryKey
                @ColumnInfo(name = "id")
                @SerializedName("id") val id: String
                ,@ColumnInfo(name = "name") val name: String = ""
                ,@ColumnInfo(name = "description") @SerializedName("description") val description: String?
                ,@ColumnInfo(name = "language") @SerializedName("language") val language: String = ""
                ,@ColumnInfo(name = "stargazers_count") @SerializedName("stargazers_count") val starCount: Int){

}