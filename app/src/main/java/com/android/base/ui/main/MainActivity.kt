package com.android.base.ui.main

import android.os.Bundle
import com.android.base.R
import com.android.base.extension.replaceFragment
import com.android.base.ui.base.BaseActivity
import com.android.base.ui.git.repo.GitRepositoryFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init View
        openGitRepository()
    }

    private fun openGitRepository() {
        replaceFragment(R.id.flContainer, GitRepositoryFragment.newInstance())
    }
}
