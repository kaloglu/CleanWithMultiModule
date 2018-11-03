package com.kaloglu.domain.rxcallback

import com.kaloglu.domain.remote.Status
import com.kaloglu.domain.remote.Error


interface ResponseCallback<T> {

    fun onResponseSuccess(response: T)

    fun onResponseError(status: Status, errors: List<Error>)

    fun onNetworkError(throwable: Throwable)

    fun onServerError(throwable: Throwable)

}