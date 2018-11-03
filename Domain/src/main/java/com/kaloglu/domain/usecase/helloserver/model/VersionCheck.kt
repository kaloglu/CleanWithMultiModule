package com.kaloglu.domain.usecase.helloserver.model

class VersionCheck(val forceUpdate: Boolean,
                   val optionalUpdate: Boolean,
                   val androidVersionName: String,
                   val androidVersionCode: String)