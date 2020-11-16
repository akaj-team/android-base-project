package com.android.appname.managers

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import javax.inject.Inject

/**
 * @author at-hungtruong
 */
class PrefManagerImpl @Inject constructor(context: Context) : PrefManager {
    private val sharedPrefManager = PreferenceManager.getDefaultSharedPreferences(context)

    override fun <Key : Enum<Key>> remove(key: Key) {
        sharedPrefManager.edit(true) { remove(key.name) }
    }

    override fun <Key : Enum<Key>> getInt(key: Key, defaultValue: Int): Int =
        sharedPrefManager.getInt(key.name, defaultValue)

    override fun <Key : Enum<Key>> setInt(key: Key, value: Int) {
        sharedPrefManager.edit(true) { putInt(key.name, value) }
    }

    override fun <Key : Enum<Key>> getLong(key: Key, defaultValue: Long): Long =
        sharedPrefManager.getLong(key.name, defaultValue)

    override fun <Key : Enum<Key>> setLong(key: Key, value: Long) {
        sharedPrefManager.edit(true) { putLong(key.name, value) }
    }

    override fun <Key : Enum<Key>> getBoolean(key: Key, defaultValue: Boolean): Boolean =
        sharedPrefManager.getBoolean(key.name, defaultValue)

    override fun <Key : Enum<Key>> setBoolean(key: Key, value: Boolean) {
        sharedPrefManager.edit(true) { putBoolean(key.name, value) }
    }

    override fun <Key : Enum<Key>> getFloat(key: Key, defaultValue: Float): Float =
        sharedPrefManager.getFloat(key.name, defaultValue)

    override fun <Key : Enum<Key>> setFloat(key: Key, value: Float) {
        sharedPrefManager.edit(true) { putFloat(key.name, value) }
    }

    override fun <Key : Enum<Key>> getString(key: Key, defaultValue: String?): String? =
        sharedPrefManager.getString(key.name, defaultValue)

    override fun <Key : Enum<Key>> setString(key: Key, value: String) {
        sharedPrefManager.edit(true) { putString(key.name, value) }
    }

    override fun clear() {
        sharedPrefManager.edit(true) {
            clear()
        }
    }
}
