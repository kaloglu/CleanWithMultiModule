package com.kaloglu.data.repository.helloserver

import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single

interface HelloServerDataStore {

    fun getHelloServer(deviceInfo: DeviceInfo): Single<HelloServer>
}