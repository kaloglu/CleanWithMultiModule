package com.kaloglu.boilerplate.managers

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


object SessionManager {

    private val publishSubject = PublishSubject.create<SessionManager>()
    private val observable = publishSubject
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())

    //TODO Bunu açıcaz sonra
//    var loginResponse: LoginResponse? = null
//        set(value) {
//            field = value
//            isLogin = !value?.token.isNullOrEmpty()
//            notify()
//        }

    private var isLogin: Boolean = false

    fun subscribe(onNext: Consumer<SessionManager>): Disposable {
        try {
            onNext.accept(this)
        } catch (e: Exception) {
            Log.e("SessionManager", "Error invoking on initial state")
        }
        return observable.subscribe(onNext)
    }

    fun validate(): Boolean {
        return isLogin
    }

//    fun getUserName(): String {
//        return loginResponse?.userDetail!!.fullName
//    }

//    fun logout() {
//        clearSession()
//        notify()
//    }

//    internal fun clearSession() {
//        loginResponse = null
//    }

    internal fun notify() {
        publishSubject.onNext(this)
    }
}