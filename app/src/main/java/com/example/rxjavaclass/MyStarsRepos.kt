package com.example.rxjavaclass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.rxjavaclass.adapter.GithubRepoAdapter
import com.example.rxjavaclass.model.Repo
import com.example.rxjavaclass.network.GithubApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_my_stars_repos.*

class MyStarsRepos : AppCompatActivity() {

    val repoList = ArrayList<Repo>()
    private lateinit var repoAdapter: GithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        myStarsList.layoutManager = linearLayoutManager

        val divider = DividerItemDecoration(myStarsList.context, DividerItemDecoration.VERTICAL)

        repoAdapter = GithubRepoAdapter()
        myStarsList.adapter = repoAdapter
        myStarsList.addItemDecoration(divider)

        getStarredRepo()
    }

    fun getStarredRepo(){

    }
}
