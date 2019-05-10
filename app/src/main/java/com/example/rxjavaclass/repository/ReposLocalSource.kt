package com.example.rxjavaclass.repository

import com.example.rxjavaclass.db.Repo
import io.reactivex.Observable

object ReposLocalSource: DataSource {
    override fun fetchRepos(userName: String): Observable<ArrayList<Repo>> {
        return Observable.empty()
    }

    override fun saveRepos(repos: ArrayList<Repo>) {

    }

}
