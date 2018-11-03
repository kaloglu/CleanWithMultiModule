package com.kaloglu.domain.remote

open class BaseResponse {
    lateinit var status: Status
    var errors: List<Error>? = null

    open fun isSuccess(): Boolean {
        return if (errors == null) true
        else
            errors!!.isEmpty()
    }

    fun <B : BaseResponse> withStatusCode(code: Int): B {
        status = Status.withCode(code)
        @Suppress("UNCHECKED_CAST")
        return this as B
    }
}