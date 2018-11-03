package com.kaloglu.boilerplate.ui.splash

import com.kaloglu.data.cache.LocalStorage
import com.kaloglu.domain.remote.Error
import com.kaloglu.domain.remote.Status
import com.kaloglu.domain.rxcallback.CallbackWrapper
import com.kaloglu.domain.usecase.helloserver.GetHelloServer
import com.kaloglu.domain.usecase.helloserver.model.DeviceInfo
import com.kaloglu.domain.usecase.helloserver.model.HelloServer
import com.kaloglu.boilerplate.helper.ConnectivityHelper
import com.kaloglu.boilerplate.helper.DeviceInfoHelper
import com.kaloglu.boilerplate.ui.base.mvp.BaseAbstractPresenter
import com.kaloglu.boilerplate.utility.navigation.Navigator
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashPresenter(
        private val helloServer: GetHelloServer,
//        private val autoLogin: GetAutoLogin,
        private val deviceInfoHelper: DeviceInfoHelper,
        private val localStorage: LocalStorage,
        private val navigator: Navigator
) : BaseAbstractPresenter<SplashContract.View>(), SplashContract.Presenter {

    val delayMainActivityObservable = Completable.complete().delay(2, TimeUnit.SECONDS)
            .doOnComplete {
//                navigator.toMainActivity()
//                        .clearTop()
//                        .singleTop()
//                        .finishThis()
//                        .navigate()
            }!!

    override fun checkConnection(connectivityHelper: ConnectivityHelper) {
        if (!connectivityHelper.isConnectedToNetwork()) {
            getView().provideDisposable().add(
                    delayMainActivityObservable.subscribe())

            return
        }

        connectivityHelper.isPingSuccess(object : ConnectivityHelper.PingCallback {
            override fun onError() {
                getView().provideDisposable().add(
                        delayMainActivityObservable.subscribe())
            }

            override fun onSuccess() {
                getHelloServer()
            }

        })
    }

    override fun getHelloServer() {
        val deviceInfo = DeviceInfo(
                deviceInfoHelper.apiLevel,
                deviceInfoHelper.appVersion,
                localStorage.getClientId(),
                deviceInfoHelper.density,
                deviceInfoHelper.deviceModel,
                deviceInfoHelper.deviceType,
                deviceInfoHelper.langKey,
                deviceInfoHelper.size,
                deviceInfoHelper.systemVersion
        )

        helloServer.execute(HelloServerSubscriber(), GetHelloServer.Params.forHelloServer(deviceInfo))
    }

    internal fun handleGetHelloServerSuccess(helloServer: HelloServer) {
        if (helloServer.versionCheck.forceUpdate) {
            getView().showForceUpdateDialog()
            return
        }

        if (helloServer.versionCheck.optionalUpdate) {
            getView().showOptionalUpdateDialog()
            return
        }
    }

    //region Subscriber
//    inner class AutoLoginSubscriber : CallbackWrapper<LoginResponse>() {
//        override fun onResponseSuccess(response: LoginResponse) {
//            SessionManager.loginResponse = response
//            localStorage.setBearerToken(response.token)
//            localStorage.setRefreshToken(response.refreshToken)
//            navigator.toMainActivity()
//                    .finishThis()
//                    .navigate()
//        }
//
//        override fun onResponseError(status: Status, errors: List<Error>) {
//            getView().showErrorMessage(status.message)
//        }
//
//        override fun onNetworkError(throwable: Throwable) {
//            //TODO handle network error
//            getView().showErrorMessage(throwable.message.toString())
//        }
//
//        override fun onServerError(throwable: Throwable) {
//            //TODO handle server error
//            getView().showErrorMessage(throwable.message.toString())
//        }
//    }

    inner class HelloServerSubscriber : CallbackWrapper<HelloServer>() {
        override fun onResponseSuccess(response: HelloServer) {
            handleGetHelloServerSuccess(response)
        }

        override fun onResponseError(status: Status, errors: List<Error>) {
            getView().showErrorMessage(status.message)
//            navigator.toMainActivity()
//                    .clearTop()
//                    .singleTop()
//                    .finishThis()
//                    .navigate()
        }

        override fun onNetworkError(throwable: Throwable) {
            getView().showErrorMessage(throwable.message.toString())
            //TODO Burada bülten acılacak
//            navigator.toMainActivity()
//                    .clearTop()
//                    .singleTop()
//                    .finishThis()
//                    .navigate()
        }

        override fun onServerError(throwable: Throwable) {
            getView().showErrorMessage(throwable.message.toString())
//            navigator.toMainActivity()
//                    .clearTop()
//                    .singleTop()
//                    .finishThis()
//                    .navigate()
        }

    }

    override fun executeAutoLogin() {
        if (!localStorage.getBearerToken().isNullOrEmpty()) {
//            autoLogin.execute(AutoLoginSubscriber(), GetAutoLogin.Params.forAutoLogin(localStorage.getBearerToken()!!))
            return
        }

        getView().hideErrorState()
//        navigator.toMainActivity().finishThis().navigate()
    }

    override fun detachView() {
        helloServer.dispose()
        super.detachView()
    }

    //endregion
}
