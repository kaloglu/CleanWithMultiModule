package com.kaloglu.data.repository.helloserver.remote

import com.kaloglu.data.repository.helloserver.remote.model.HelloServerRequest
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface HelloServerService {

    @POST("handshake")
    fun helloServer(@Body request: HelloServerRequest): Single<HelloServer>
}

