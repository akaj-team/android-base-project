package com.android.appname.data.source.remote.network

import com.google.gson.annotations.SerializedName

data class ApiException(
    @SerializedName("error_code") val errorCode: Int?,
    @SerializedName("message") val errorMessage: String?
) : Throwable(errorMessage)
