package com.android.base.data.source.remote

import com.android.base.data.source.datasource.DataSource
import com.android.base.data.source.remote.network.ApiClient
import com.android.base.data.source.remote.network.ApiService

class RemoteDataSource(private val apiService: ApiService) : DataSource {
    constructor() : this(ApiClient.getInstance(null).service)
}
