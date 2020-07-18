package com.android.appname.api

import com.android.appname.api.element.GitRemoteDataSourceTest
import com.android.appname.data.source.remote.network.ApiClient
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GitRemoteDataSourceTest::class
)
class ApiSuiteTest {

    companion object {
        internal val server = MockWebServer()
        internal lateinit var baseUrl: HttpUrl

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            ApiClient.clearInstance()
            try {
                server.start()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
            baseUrl = server.url("/")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            server.shutdown()
            ApiClient.clearInstance()
        }

        internal val apiClient: ApiClient
            get() = ApiClient.getInstance(baseUrl.toString())
    }
}
