package com.android.appname.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VersionInfo(
    val isEnable: Boolean? = false,
    val isContainsAd: Boolean = false,
    val tournamentChecker: String? = "",
)
