package com.android.appname.data.source

import com.android.appname.data.source.datasource.DataSource
import com.android.appname.data.source.remote.RemoteDataSource

class Repository : DataSource {
    private val remoteDataSource = RemoteDataSource()
}
