package com.android.appname.data.source.remote

import com.android.appname.data.source.datasource.DataSource
import com.android.appname.data.source.remote.network.ApiClient
import com.android.appname.data.source.remote.network.ApiService

class RemoteDataSource(private val apiService: ApiService) : DataSource {
    constructor() : this(ApiClient.getInstance(null).service)
}
