package com.android.appname.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LicenseEntity(
     @ColumnInfo(name = "license_id")
     @PrimaryKey
     var licenseId: Long = 0,
     @ColumnInfo(name = "key")
     var key: String? = null,
     @ColumnInfo(name = "license_name")
     var name: String? = null,
     @ColumnInfo(name = "spdx_id")
     var spdxId: String? = null,
     @ColumnInfo(name = "license_url")
     var url: String? = null,
     @ColumnInfo(name = "license_node_id")
     var nodeId: String? = null
)
