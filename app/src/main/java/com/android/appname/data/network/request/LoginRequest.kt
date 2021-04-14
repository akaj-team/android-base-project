package com.android.appname.data.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author at-vinh.huynh
 */
@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)
