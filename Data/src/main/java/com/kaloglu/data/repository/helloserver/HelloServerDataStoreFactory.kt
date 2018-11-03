package com.kaloglu.data.repository.helloserver

import com.kaloglu.data.repository.helloserver.remote.HelloServerRemoteDataStore
import javax.inject.Inject

class HelloServerDataStoreFactory @Inject constructor(
        private val helloServerRemoteDataStore: HelloServerRemoteDataStore) {

    fun getDataStore(): HelloServerDataStore {
        return helloServerRemoteDataStore
    }
}
