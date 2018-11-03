package com.kaloglu.domain.usecase.helloserver.model

import com.kaloglu.domain.remote.BaseResponse

class HelloServer constructor(
        val sessionId: String,
        val content: HandshakeContent,
        val versionCheck: VersionCheck) : BaseResponse()
