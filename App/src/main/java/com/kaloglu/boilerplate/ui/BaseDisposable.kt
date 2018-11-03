package com.kaloglu.boilerplate.ui

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface BaseDisposable {
    var baseDisposables: CompositeDisposable

    fun addBaseDisposable(d: Disposable) {
        baseDisposables.add(d)
    }

    fun disposeBaseDisposables() {
        if (baseDisposables.isDisposed) baseDisposables.dispose()
    }
}