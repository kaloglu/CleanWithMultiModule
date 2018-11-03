package com.kaloglu.data.repository

interface CacheMapper<C, D> {

    fun mapFromCache(cacheModel: C): D

    fun mapToCache(domainModel: D): C
}