package com.android.appname.arch.util

import com.android.appname.data.repositories.LocalRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 *
 * @author at-vinh.huynh
 */
class UnAuthorizeInterceptor(
    private val localRepository: LocalRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (route?.address?.url?.toString()?.contains("auth/refresh") == true) {
            return null
        }
        return runBlocking {
            null
            // refresh toke
        }
    }
}
