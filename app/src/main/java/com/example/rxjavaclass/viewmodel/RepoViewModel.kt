package com.example.rxjavaclass.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rxjavaclass.db.Repo
import com.example.rxjavaclass.network.GithubApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel: ViewModel() {

    val compositeDisposible = CompositeDisposable()
    val repoLiveData = MutableLiveData<ArrayList<Repo>>()

    fun getMyStartsRepos(username: String){
        val reposDisposable = GithubApiClient.getGithubServices().getStarredRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    it -> repoLiveData.value = it
            }
        compositeDisposible.add(reposDisposable)
    }

    fun getLiveData(): LiveData<ArrayList<Repo>> = repoLiveData

    //this function will be called when this model kill
    override fun onCleared() {
        super.onCleared()

        compositeDisposible.clear()
    }

}