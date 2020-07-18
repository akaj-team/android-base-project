package com.android.base.data.source

import com.android.base.data.source.datasource.DataSource
import com.android.base.data.source.remote.RemoteDataSource

class Repository : DataSource {
    private val remoteDataSource = RemoteDataSource()
}
