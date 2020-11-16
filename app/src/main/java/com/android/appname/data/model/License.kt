package com.android.appname.data.model

import com.android.appname.data.entities.LicenseEntity
import com.google.gson.annotations.SerializedName
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

data class License(
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("spdx_id") val spdxId: String,
    @SerializedName("url") val url: String,
    @SerializedName("node_id") val nodeId: String
) {
    fun toEntity() = LicenseEntity().apply {
        License::class.memberProperties.forEach { field ->
            (LicenseEntity::class.memberProperties.find { it.name == field.name } as? KMutableProperty<*>)
                ?.setter
                ?.call(this, field.get(this@License))
        }
    }
}
