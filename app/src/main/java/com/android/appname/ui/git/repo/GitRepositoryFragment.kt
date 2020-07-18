package com.android.appname.ui.git.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.appname.R
import com.android.appname.data.source.GitRepository
import com.android.appname.extension.observeOnUiThread
import com.android.appname.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_git_repo.*

class GitRepositoryFragment : BaseFragment() {
    companion object {
        fun newInstance(): GitRepositoryFragment {
            return GitRepositoryFragment()
        }
    }

    private val viewModel: GitRepositoryVMContract by lazy {
        GitRepositoryViewModel(GitRepository())
    }
    private val adapter: GitRepoAdapter by lazy {
        GitRepoAdapter(viewModel.gitRepoList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    override fun onBindViewModel() = Unit

    private fun initViews() {
        rvGitRepo.adapter = adapter
    }

    private fun initData() {
        viewModel.getRepositories()
            .observeOnUiThread()
            .subscribe({ adapter.notifyDataSetChanged() }, {})
    }
}
