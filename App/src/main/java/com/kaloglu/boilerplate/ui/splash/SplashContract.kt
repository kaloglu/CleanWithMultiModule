package com.kaloglu.boilerplate.ui.splash

import com.kaloglu.boilerplate.helper.ConnectivityHelper
import com.kaloglu.boilerplate.ui.base.mvp.BasePresenter
import com.kaloglu.boilerplate.ui.base.mvp.BaseView
import io.reactivex.disposables.CompositeDisposable

interface SplashContract {

    interface View : BaseView {
        fun provideDisposable(): CompositeDisposable
        fun showErrorMessage(message: String)
        fun hideErrorState()
        fun showForceUpdateDialog()
        fun showOptionalUpdateDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun checkConnection(connectivityHelper: ConnectivityHelper)
        fun getHelloServer()
        fun executeAutoLogin()
    }
}
