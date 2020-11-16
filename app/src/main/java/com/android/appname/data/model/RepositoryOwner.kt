package com.android.appname.data.model

import com.android.appname.data.entities.RepositoryOwnerEntity
import com.google.gson.annotations.SerializedName
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

data class RepositoryOwner(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("gravatar_id") val gravatarId: String,
    @SerializedName("url") val url: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("gists_url") val gistsUrl: String,
    @SerializedName("starred_url") val starredUrl: String,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String,
    @SerializedName("organizations_url") val organizationsUrl: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("received_events_url") val receivedEventsUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val siteAdmin: Boolean
) {
    fun toEntity() = RepositoryOwnerEntity().apply {
        RepositoryOwner::class.memberProperties.forEach { field ->
            (RepositoryOwnerEntity::class.memberProperties.find { it.name == field.name } as? KMutableProperty<*>)
                ?.setter
                ?.call(this, field.get(this@RepositoryOwner))
        }
    }
}
