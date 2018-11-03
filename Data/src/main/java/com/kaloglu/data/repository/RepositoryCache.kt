package com.kaloglu.data.repository

interface RepositoryCache {

    fun getRepositoryKey(): String

    fun isCached(): Boolean

    fun getLastCacheTime(): Long

    fun setLastCacheTime(lastCache: Long)

    fun isCacheExpired(): Boolean
}