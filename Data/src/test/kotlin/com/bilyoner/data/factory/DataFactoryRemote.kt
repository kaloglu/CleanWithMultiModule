package com.kaloglu.boiler_plate.data.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactoryRemote {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }
}