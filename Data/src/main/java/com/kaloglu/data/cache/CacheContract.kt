package com.kaloglu.data.cache

interface CacheContract {

    companion object {
        private const val CACHE_TTL = 5 * 60 * 1000L //TTL: 5 minute
    }

    fun getCacheTTL(): Long {
        return CACHE_TTL
    }

    fun now(): Long {
        return System.currentTimeMillis()
    }

}
