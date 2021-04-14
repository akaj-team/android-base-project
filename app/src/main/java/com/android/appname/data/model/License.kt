package com.android.appname.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author mvn-vinhhuynh-dn
 */
@Serializable
data class License(
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("spdx_id") val spdxId: String,
    @SerialName("url") val url: String,
    @SerialName("node_id") val nodeId: String
)
