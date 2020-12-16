package com.android.appname.data.source.remote.network

import com.android.appname.data.model.ErrorResponse
import com.android.appname.data.model.RestResultWrapper
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * @author at-hungtruong
 */
class SafeApiCallService @Inject constructor(private val gson: Gson) {
    companion object {
        private const val UNAUTHORIZED_STATUS_CODE = 401
    }

    suspend fun <T> call(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        predicated: suspend () -> T
    ): RestResultWrapper<T> = withContext(dispatcher) {
        try {
            RestResultWrapper.Success(throwExceptionOnSuccessOrReturnResponse(predicated()))
        } catch (throwable: Throwable) {
            when (throwable) {
                is UnknownHostException, is ConnectException, is SocketTimeoutException, is IOException -> {
                    RestResultWrapper.NetworkError(throwable)
                }
                is HttpException -> {
                    if (throwable.code() == UNAUTHORIZED_STATUS_CODE) {
                        RestResultWrapper.UnauthorizedError
                    } else {
                        RestResultWrapper.GenericError(
                            throwable.code(),
                            convertErrorBody(throwable),
                            throwable
                        )
                    }
                }
                else -> {
                    RestResultWrapper.GenericError(throwable = throwable)
                }
            }
        }
    }

    private fun <T> throwExceptionOnSuccessOrReturnResponse(response: Any?): T {
        @Suppress("UNCHECKED_CAST")
        return response as T
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? = try {
        throwable.response()?.errorBody()?.string()?.let {
            gson.fromJson(it, ErrorResponse::class.java)
        }
    } catch (e: Exception) {
        null
    }
}
