package com.android.appname.data.model

import com.android.appname.data.exceptions.UnauthorizedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

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

    internal fun toFlow(context: CoroutineContext = Dispatchers.Default) =
        flowOf(this).flowOn(context)
}

fun <T> T.toResultSuccess() = RestResultWrapper.Success(this)
