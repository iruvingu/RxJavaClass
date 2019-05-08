package com.example.rxjavaclass.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxjavaclass.R
import com.example.rxjavaclass.model.Repo
import kotlinx.android.synthetic.main.row_stars_item.view.*

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.StarRepoViewHolder>() {

    val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_stars_item, parent, false)
        return StarRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: StarRepoViewHolder, position: Int) {
        viewHolder.repoName.text = data[position].name

        viewHolder.repoLang.text = data[position].language
        viewHolder.repoCount.text = data[position].starCount.toString()

        data[position].description?.let {
            viewHolder.repoDesc.text = data[position].description
        }?:run {
            viewHolder.repoDesc.text = "NO DESC"
        }
    }

    fun addRepos(repos: ArrayList<Repo>) {
        data.addAll(repos)
        notifyDataSetChanged()
    }

    class StarRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val repoName = itemView.repoName
        val repoDesc = itemView.desc
        val repoLang = itemView.lang
        val repoCount = itemView.starsCount

    }

}