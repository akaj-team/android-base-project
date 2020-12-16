package com.android.appname.ui.git.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.appname.R
import com.android.appname.extension.requestApi
import com.android.appname.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_git_repo.*
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

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
        /*lifecycleScope.launch {
            viewModel.getLoadingState().collect { setLoadingDialogVisibility(it) }
        }*/
        initViews()
        requestRepositories()
    }

    private fun initViews() {
        rvGitRepo.adapter = GitRepoAdapter(viewModel.gitRepositories())
        swipeRefresh.setOnRefreshListener {
            requestRepositories()
        }
    }

    private fun requestRepositories() {
        lifecycleScope.launch {
            requestApi(viewModel.requestRepositories().onCompletion {
                swipeRefresh.isRefreshing = false
            }, {
                rvGitRepo.adapter?.notifyDataSetChanged()
            })
        }
    }
}
