package com.android.appname.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author at-vinh.huynh
 */
@Serializable
data class BaseResponse(
    @SerialName("message") val message: String? = ""
)
