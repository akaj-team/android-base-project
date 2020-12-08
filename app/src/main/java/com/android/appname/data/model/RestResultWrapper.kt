package com.android.appname.data.model

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

    internal fun onResultResponsed(
        onSuccess: (Success<T>) -> Unit,
        onFailure: (Failure) -> Unit
    ) {
        if (this is Success) {
            onSuccess(this)
        } else if (this is Failure) {
            onFailure(this)
        }
    }
}

fun <T> T.toResultSuccess() = RestResultWrapper.Success(this)
