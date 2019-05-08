package com.example.rxjavaclass.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rxjavaclass.model.Repo
import com.example.rxjavaclass.network.GithubApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoViewModel: ViewModel() {

    val repoLiveData = MutableLiveData<List<Repo>>()

    fun getMyStartsRepos(username: String){
        GithubApiClient.getGithubServices().getStarredRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    it -> repoLiveData.value = it
            }
    }

    //this function will be called when this model kill
    override fun onCleared() {
        super.onCleared()
    }

}