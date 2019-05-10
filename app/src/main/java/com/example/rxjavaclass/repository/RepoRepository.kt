package com.example.rxjavaclass.repository

import com.example.rxjavaclass.db.Repo
import io.reactivex.Observable

class RepoRepository(val repoRemoteSource: RepoRsRemoteSources
                     , val repoLocalSource: ReposLocalSource): DataSource{
    override fun fetchRepos(userName: String): Observable<ArrayList<Repo>> {
        return Observable.empty()
    }

    override fun saveRepos(repos: ArrayList<Repo>) {

    }
}