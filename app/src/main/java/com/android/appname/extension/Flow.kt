package com.android.appname.extension

import com.android.appname.data.model.RestResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

/**
 * @author at-hungtruong
 */
suspend fun <R> Flow<RestResultWrapper<R>>.zipSuccessResult(
    other: Flow<RestResultWrapper<R>>,
    transform: suspend (RestResultWrapper.Success<R>, RestResultWrapper.Success<R>) -> RestResultWrapper<R>
): Flow<RestResultWrapper<R>> = zip(other) { thisValue, otherValue ->
    when {
        this !is RestResultWrapper.Success<*> -> {
            thisValue
        }
        other !is RestResultWrapper.Success<*> -> {
            otherValue
        }
        else -> {
            transform(
                thisValue as RestResultWrapper.Success<R>,
                otherValue as RestResultWrapper.Success<R>
            )
        }
    }
}
