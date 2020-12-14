package com.android.appname.data.exceptions

import com.android.appname.data.model.ErrorResponse

/**
 * @author at-hungtruong
 */
data class ApiGenericException(
    val code: Int,
    val error: ErrorResponse? = null,
    val throwable: Throwable? = null
) : Throwable()
