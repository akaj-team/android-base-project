package com.android.appname.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.android.appname.R
import com.android.appname.extension.replaceFragment
import com.android.appname.ui.base.BaseActivity
import com.android.appname.ui.git.repo.GitRepositoryFragment

class MainActivity : BaseActivity() {

    private val viewModel : MainVMContract by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init View
        openGitRepository()
        viewModel.log()
    }

    private fun openGitRepository() {
        replaceFragment(R.id.flContainer, GitRepositoryFragment.newInstance())
    }
}
