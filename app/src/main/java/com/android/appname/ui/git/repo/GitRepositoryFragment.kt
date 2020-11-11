package com.android.appname.ui.git.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.appname.R
import com.android.appname.extension.observeOnUiThread
import com.android.appname.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_git_repo.*

class GitRepositoryFragment : BaseFragment() {
    companion object {
        fun newInstance(): GitRepositoryFragment {
            return GitRepositoryFragment()
        }
    }

    private val viewModel: GitRepositoryVMContract by viewModels<GitRepositoryViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_git_repo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    override fun onBindViewModel() = Unit

    private fun initViews() {
        rvGitRepo.adapter = GitRepoAdapter(viewModel.gitRepoList())
    }

    private fun initData() {
        viewModel.getRepositories()
            .observeOnUiThread()
            .subscribe({ rvGitRepo.adapter?.notifyDataSetChanged() }, {})
    }
}
