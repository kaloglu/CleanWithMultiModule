package com.kaloglu.data.repository.helloserver.remote.model

data class HelloServerRequest(val apiLevel: Int,
                              val appVersion: String,
                              val clientId: String,
                              val density: String,
                              val deviceModel: String,
                              val deviceType: String,
                              val langKey: String,
                              val size: Int,
                              val systemVersion: String)