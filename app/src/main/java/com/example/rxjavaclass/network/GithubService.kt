package com.example.rxjavaclass.network

import com.example.rxjavaclass.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    //First endpoint
    @GET("users/{user}/starred")
    fun getStarredRepos(@Path("user") username: String): Observable<ArrayList<Repo>>
}