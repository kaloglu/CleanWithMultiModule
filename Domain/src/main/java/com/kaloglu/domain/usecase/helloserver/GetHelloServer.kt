package com.kaloglu.domain.usecase.helloserver

import com.kaloglu.domain.executor.PostExecutionThread
import com.kaloglu.domain.usecase.SingleUseCase
import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single
import javax.inject.Inject

class GetHelloServer @Inject constructor(
        private val mHelloServerRepository: HelloServerRepository,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<HelloServer, GetHelloServer.Params>(postExecutionThread) {

    override fun buildUseCaseSingle(vararg params: Params): Single<HelloServer> {
        return this.mHelloServerRepository.helloServer(params.first().deviceInfo)
    }

    data class Params constructor(val deviceInfo: DeviceInfo) {
        companion object {
            fun forHelloServer(deviceInfo: DeviceInfo): Params {
                return Params(deviceInfo)
            }
        }
    }

}
