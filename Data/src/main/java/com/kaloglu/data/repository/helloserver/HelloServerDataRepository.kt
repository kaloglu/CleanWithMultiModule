package com.kaloglu.data.repository.helloserver

import com.kaloglu.domain.usecase.helloserver.HelloServerRepository
import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single
import javax.inject.Inject

class HelloServerDataRepository @Inject constructor(
        private val factory: HelloServerDataStoreFactory
) : HelloServerRepository {

    override fun helloServer(deviceInfo: DeviceInfo): Single<HelloServer> {
        val dataStore = factory.getDataStore()
        return dataStore.getHelloServer(deviceInfo)
    }
}
