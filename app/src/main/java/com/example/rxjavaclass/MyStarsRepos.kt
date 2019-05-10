package com.example.rxjavaclass

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.rxjavaclass.adapter.GithubRepoAdapter
import com.example.rxjavaclass.db.Repo
import com.example.rxjavaclass.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_my_stars_repos.*

class MyStarsRepos : AppCompatActivity() {

    val repoList = ArrayList<Repo>()
    private lateinit var repoAdapter: GithubRepoAdapter
    private lateinit var repoViewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        myStarsList.layoutManager = linearLayoutManager

        val divider = DividerItemDecoration(myStarsList.context, DividerItemDecoration.VERTICAL)

        repoAdapter = GithubRepoAdapter()
        myStarsList.adapter = repoAdapter
        myStarsList.addItemDecoration(divider)

        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)

        getStarredRepo(repoViewModel)

        observeMyStars(repoViewModel)
    }

    private fun getStarredRepo(viewModel: RepoViewModel) {
        viewModel.getMyStartsRepos("mrabelwahed")
    }

    private fun observeMyStars(viewModel: RepoViewModel) {
        viewModel.getLiveData().observe(this, Observer {
            repos -> repoAdapter.addRepos(repos!!)
        })
    }
}
