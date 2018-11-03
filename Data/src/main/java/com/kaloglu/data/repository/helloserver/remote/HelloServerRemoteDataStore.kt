package com.kaloglu.data.repository.helloserver.remote

import com.kaloglu.data.repository.helloserver.HelloServerDataStore
import com.kaloglu.data.repository.helloserver.remote.model.HelloServerRequest
import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single
import javax.inject.Inject

class HelloServerRemoteDataStore @Inject constructor(
        private val service: HelloServerService
) : HelloServerDataStore {

    override fun getHelloServer(deviceInfo: DeviceInfo): Single<HelloServer> {
        return service.helloServer(HelloServerRequest(
                deviceInfo.apiLevel,
                deviceInfo.appVersion,
                deviceInfo.clientId,
                deviceInfo.density,
                deviceInfo.deviceModel,
                deviceInfo.deviceType,
                deviceInfo.langKey,
                deviceInfo.size,
                deviceInfo.systemVersion))
    }
}