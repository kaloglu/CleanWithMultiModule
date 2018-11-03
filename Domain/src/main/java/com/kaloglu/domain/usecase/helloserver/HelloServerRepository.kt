package com.kaloglu.domain.usecase.helloserver

import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single

interface HelloServerRepository {

    fun helloServer(deviceInfo: DeviceInfo): Single<HelloServer>

}
