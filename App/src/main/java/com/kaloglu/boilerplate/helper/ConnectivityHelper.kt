package com.kaloglu.boilerplate.helper

import android.content.Context
import com.kaloglu.data.remote.connectivity.Connectivity
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class ConnectivityHelper @Inject constructor(
        @ApplicationContext private val context: Context) {

    interface PingCallback {
        fun onSuccess()
        fun onError()
    }

    private val pingObservable = Maybe
            .fromCallable {
                return@fromCallable Connectivity.isPingSuccess()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun isConnectedToNetwork(): Boolean {
        return Connectivity.isConnectedToNetwork(context)
    }

    fun isPingSuccess(pingCallback: PingCallback) {
        pingObservable.subscribe { t: Boolean? ->
            if (t != null && t) pingCallback.onSuccess()
            else
                pingCallback.onError()
        }
    }

}