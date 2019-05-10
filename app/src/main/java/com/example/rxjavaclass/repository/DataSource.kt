package com.example.rxjavaclass.repository

import com.example.rxjavaclass.db.Repo
import io.reactivex.Observable

interface DataSource {
    fun fetchRepos(userName: String): Observable<ArrayList<Repo>>
    fun saveRepos(repos: ArrayList<Repo>)
}