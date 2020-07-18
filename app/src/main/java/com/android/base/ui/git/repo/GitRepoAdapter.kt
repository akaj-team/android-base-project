package com.android.base.ui.git.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.base.R
import com.android.base.data.source.remote.response.GitRepoResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_git_repo.view.*

class GitRepoAdapter(private val gitRepoList: List<GitRepoResponse>) :
    RecyclerView.Adapter<GitRepoAdapter.GitRepoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoVH {
        return GitRepoVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_git_repo, parent, false)
        )
    }

    override fun getItemCount(): Int = gitRepoList.size

    override fun onBindViewHolder(holder: GitRepoVH, position: Int) {
        holder.onBind(gitRepoList[position])
    }

    inner class GitRepoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(repo: GitRepoResponse) {
            itemView.run {
                Glide.with(imgAvatar).load(repo.owner.avatarUrl).into(imgAvatar)
                tvTitle.text = repo.name
                tvDescription.text = repo.description
                tvOwner.text = repo.owner.login
            }
        }
    }
}
