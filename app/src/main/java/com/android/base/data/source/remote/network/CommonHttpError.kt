package com.android.base.data.source.remote.network

import java.net.HttpURLConnection

enum class CommonHttpError(val code: Int, val msg: String) {
    NO_NETWORK_EXCEPTION(2002, "NO_NETWORK_EXCEPTION"),
    CONNECTION_TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT, "CONNECTION_TIMEOUT"),
    UN_HANDLING_ERROR(2000, "UN_HANDLING_ERROR")
}
