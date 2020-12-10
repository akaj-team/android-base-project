package com.android.appname.viewmodel

import com.android.appname.BaseTest
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.git.repo.GitRepositoryViewModel
import com.android.appname.util.RxSchedulersOverrideRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GitRepositoryViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var gitRepository: GitRepository

    private lateinit var viewModel: GitRepositoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = GitRepositoryViewModel(gitRepository)
    }

    @Test
    fun `Given Nothing - When call constructor GitRepositoryViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        MatcherAssert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given git repository list - When call getRepoList - Then return correct result`() {
        /* Given */

        /* When */
        val gitRepositories = viewModel.gitRepoList()

        /* Then */
        Assert.assertEquals(gitRepositories.size, 0)
    }

    @Test
    fun `Given since is any - When call getRepositories - Then return correct result`() {
        /* Given */
        val testObserver = TestObserver<MutableList<GitRepoResponse>>()
        val response = mutableListOf<GitRepoResponse>()
        /* When */
        `when`(gitRepository.getRepositories(Mockito.anyLong())).thenReturn(Single.just(response))
        viewModel.getRepositories(1L).subscribe(testObserver)

        /* Then */
        testObserver.assertValue {
            MatcherAssert.assertThat(it, CoreMatchers.`is`(response))
            true
        }
    }
}
