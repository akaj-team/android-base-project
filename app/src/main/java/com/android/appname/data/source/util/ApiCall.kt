package com.android.appname.data.source.util

import com.android.appname.data.exceptions.ApiGenericException
import com.android.appname.data.exceptions.TimeoutException
import com.android.appname.data.exceptions.UnauthorizedException
import com.android.appname.data.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * @author at-hungtruong
 */
class ApiCall<T> {
    companion object {
        private const val UNAUTHORIZED_STATUS_CODE = 400
    }

    internal var onSuccess: ((T) -> Unit)? = null
    internal var onFailure: ((Throwable) -> Unit)? = null
    internal var onFinally: (() -> Unit)? = null
    internal var call: (suspend () -> T)? = null
    internal var doOnStart: (() -> Unit)? = null

    @set:Inject
    var gson: Gson? = null

    suspend fun apiCall(predicate: suspend ApiCall<T>.() -> Unit): ApiCall<T> {
        val apiCall = ApiCall<T>()
        predicate.invoke(apiCall)
        return apiCall
    }

    suspend operator fun invoke() {
        requireNotNull(call)
        doOnStart?.invoke()
        val result = call?.invoke()
        try {
            result?.let { onSuccess?.invoke(it) }
        } catch (throwable: Throwable) {
            when (throwable) {
                is UnknownHostException, is ConnectException, is SocketTimeoutException, is IOException -> {
                    onFailure?.invoke(TimeoutException)
                }
                is HttpException -> {
                    if (throwable.code() == UNAUTHORIZED_STATUS_CODE) {
                        onFailure?.invoke(UnauthorizedException)
                    } else {
                        onFailure?.invoke(
                            ApiGenericException(
                                throwable.code(),
                                convertErrorBody(throwable),
                                throwable
                            )
                        )
                    }
                }
                else -> {
                    onFailure?.invoke(throwable)
                }
            }
        }
        onFinally?.invoke()
    }

    private fun <T> throwExceptionOnSuccessOrReturnResponse(response: Any?): T {
        @Suppress("UNCHECKED_CAST")
        return response as T
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? = try {
        throwable.response()?.errorBody()?.string()?.let {
            gson?.fromJson(it, ErrorResponse::class.java)
        }
    } catch (e: Exception) {
        null
    }
}
