package com.kaloglu.data.cache

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.*

class LocalStorage(val context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private const val BULLETIN_LAST_VERSION = "bulletin_last_version"
        private const val BULLETIN_LAST_CACHE_DATE = "bulletin_last_cache_date"
        private const val FINGERPRINT_AUTHENTICATION_KEY = "fingerprint_authentication_key"
        private const val FINGERPRINT_AUTHENTICATION_SHOW_KEY = "fingerprint_authentication_show_key"
        private const val CLIENT_ID = "client_id"
        private const val BEARER_TOKEN = "token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN_EXPIRE_DATE = "access_token_expire_date"
        private const val REFRESH_TOKEN_EXPIRE_DATE = "refresh_token_expire_date"

    }

    //region BULLETIN
    fun setBulletinLastCacheVersion(version: Int) =
            setValue(BULLETIN_LAST_VERSION, version)

    fun getBulletinLastCacheVersion(): Int =
            preferences.getInt(BULLETIN_LAST_VERSION, 0)

    fun setBulletinLastCacheDate(date: Long) =
            setValue(BULLETIN_LAST_CACHE_DATE, date)

    fun getBulletinLastCacheDate(): Long =
            preferences.getLong(BULLETIN_LAST_CACHE_DATE, Long.MIN_VALUE)

    //endregion

    //region TOKEN and REFRESH TOKEN

    fun setBearerToken(token: String?) {
        setValue(BEARER_TOKEN, token)
    }

    fun getBearerToken(): String? =
            preferences.getString(BEARER_TOKEN, null)

    fun clearBearerToken() =
            preferences.edit().remove(BEARER_TOKEN).apply()

    fun setRefreshToken(refreshToken: String) {
        setValue(REFRESH_TOKEN, refreshToken)
    }

    fun getRefreshToken(): String? =
            preferences.getString(REFRESH_TOKEN, null)

    fun clearRefreshToken() =
            preferences.edit().remove(REFRESH_TOKEN).apply()

    fun setAccessTokenExpireDate(date: String) {
        setValue(ACCESS_TOKEN_EXPIRE_DATE, date.toLong())
    }

    fun setRefreshTokenExpireDate(date: String) {
        setValue(REFRESH_TOKEN_EXPIRE_DATE, date.toLong())
    }

    fun getAccessTokenExpireDate() =
            preferences.getLong(ACCESS_TOKEN_EXPIRE_DATE, 0)

    fun clearAccessTokenExpireDate() {
        setValue(ACCESS_TOKEN_EXPIRE_DATE,0)
    }

    fun getRefreshTokenExpireDate() =
            preferences.getLong(REFRESH_TOKEN_EXPIRE_DATE, 0)

    //endregion

    //region FINGERPRINTS
    fun setFingerPrintsAuthentication(authentication: Boolean) =
            setValue(FINGERPRINT_AUTHENTICATION_KEY, authentication)

    fun getFingerPrintsAuthentication(): Boolean =
            preferences.getBoolean(FINGERPRINT_AUTHENTICATION_KEY, false)

    fun setFingerPrintsShowAuthentication(authentication: Boolean) =
            setValue(FINGERPRINT_AUTHENTICATION_SHOW_KEY, authentication)

    fun getFingerPrintsShowAuthentication(): Boolean =
            preferences.getBoolean(FINGERPRINT_AUTHENTICATION_SHOW_KEY, true)

    //endregion

    fun getClientId(): String {
        var id = preferences.getString(CLIENT_ID, "")
        if (id.isNullOrEmpty()) {
            id = UUID.randomUUID().toString()
            setValue(CLIENT_ID, id)
        }
        return id
    }

    private fun edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = preferences.edit()
        operation(editor)
        editor.apply()
    }

    private fun setValue(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}