package com.android.appname.ui.git.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.appname.R
import com.android.appname.ui.base.BaseActivity
import com.android.appname.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_git_repo.*
import kotlinx.coroutines.channels.consumeEach
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
        lifecycleScope.launch {
            viewModel.getLoadingChannel().consumeEach {
                (activity as? BaseActivity)?.setLoadingDialogVisibility(it)
            }
        }
        initViews()
        initData()
    }

    override fun onBindViewModel() = Unit

    private fun initViews() {
        rvGitRepo.adapter = GitRepoAdapter(viewModel.gitRepoList())
        swipeRefresh.setOnRefreshListener {
            viewModel.getRepositorySuspend {
                rvGitRepo.adapter?.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initData() {
        viewModel.getRepositorySuspend {
            rvGitRepo.adapter?.notifyDataSetChanged()
        }
    }
}
