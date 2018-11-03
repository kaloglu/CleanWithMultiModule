package com.kaloglu.domain.remote

class Status(val code: Int,
             val message: String) {

    companion object {
        fun withCode(code: Int): Status {
            return Status(code, "No server message obtained.")
        }
    }
}