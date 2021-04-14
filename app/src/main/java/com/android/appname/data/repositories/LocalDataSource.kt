package com.android.appname.data.repositories

/**
 *
 * @author at-vinh.huynh
 */
interface LocalDataSource {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String?)
    fun getTermAccept(): Boolean
    fun saveTermAccept(status: Boolean?)
}
