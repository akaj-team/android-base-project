package com.android.appname.managers

/**
 * @author at-hungtruong
 */
interface PrefManager {
    fun <Key : Enum<Key>> getInt(key: Key, defaultValue: Int = 0): Int

    fun <Key : Enum<Key>> setInt(key: Key, value: Int)

    fun <Key : Enum<Key>> getLong(key: Key, defaultValue: Long = 0L): Long

    fun <Key : Enum<Key>> setLong(key: Key, value: Long)

    fun <Key : Enum<Key>> getBoolean(key: Key, defaultValue: Boolean = false): Boolean

    fun <Key : Enum<Key>> setBoolean(key: Key, value: Boolean)

    fun <Key : Enum<Key>> getFloat(key: Key, defaultValue: Float = 0F): Float

    fun <Key : Enum<Key>> setFloat(key: Key, value: Float)

    fun <Key : Enum<Key>> getString(key: Key, defaultValue: String? = null): String?

    fun <Key : Enum<Key>> setString(key: Key, value: String)

    fun <Key : Enum<Key>> remove(key: Key)

    fun clear()
}
