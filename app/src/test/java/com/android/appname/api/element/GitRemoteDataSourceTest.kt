package com.android.appname.api.element

import com.android.appname.BaseTest
import com.android.appname.api.ApiSuiteTest
import com.android.appname.api.ApiSuiteTest.Companion.server
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.source.remote.GitRemoteDataSource
import com.android.appname.extension.addResponseBody
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class GitRemoteDataSourceTest : BaseTest() {

    @Test
    fun `Given nothing - When call GitRemoteDataSource secondary constructor - Then do nothing`() {
        /* Given */

        /* When */
        GitRemoteDataSource()

        /* Then */
        assert(true)
    }

    @Test
    fun `Given nothing - When call getRepositories  - Then return correct result`() {
        /* Given */
        val test = TestObserver<MutableList<GitRepoResponse>>()

        server.addResponseBody("GithubRepositories.json")

        /* When */
        GitRemoteDataSource(ApiSuiteTest.apiClient.service).getRepositories(1L).subscribe(test)

        /* Then */
        test.await().assertValue {
            MatcherAssert.assertThat(it.size, CoreMatchers.`is`(100))
            true
        }
    }
}
