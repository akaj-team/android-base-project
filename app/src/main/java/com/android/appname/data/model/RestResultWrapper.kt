package com.android.appname.data.model

import com.android.appname.data.exceptions.UnauthorizedException

/**
 * @author at-hungtruong
 */
sealed class RestResultWrapper<out T> {
    data class Success<out T>(val value: T) : RestResultWrapper<T>()

    open class Failure(open val throwable: Throwable? = null) : RestResultWrapper<Nothing>()

    data class GenericError(
        val code: Int? = null,
        val error: ErrorResponse? = null,
        override val throwable: Throwable? = null
    ) : Failure(throwable)

    data class NetworkError(override val throwable: Throwable?) : Failure(throwable)

    object UnauthorizedError : Failure(UnauthorizedException)

    internal fun doOnResultResponded(
        onSuccess: (Success<T>) -> Unit,
        onFailure: ((Failure) -> Unit)? = null
    ): RestResultWrapper<T> {
        if (this is Success) {
            onSuccess(this)
        } else if (this is Failure) {
            onFailure?.let { it(this) }
        }
        return this
    }
}

fun <T> T.toResultSuccess() = RestResultWrapper.Success(this)
