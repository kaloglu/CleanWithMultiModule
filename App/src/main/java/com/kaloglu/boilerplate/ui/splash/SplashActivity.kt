package com.kaloglu.boilerplate.ui.splash

import com.kaloglu.boilerplate.R
import com.kaloglu.boilerplate.dialogs.DialogHelper
import com.kaloglu.boilerplate.ui.base.BaseFragment
import com.kaloglu.boilerplate.ui.base.mvp.BaseMvpActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashActivity
    : BaseMvpActivity<SplashContract.Presenter>(), SplashContract.View {

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun getContentResourceId(): Int {
        return R.layout.activity_splash
    }

    override fun getContainedFragment(): BaseFragment? = null

    override fun initUserInterface() = Unit

    override fun provideDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    override fun onPresenterAttached() {
        presenter.checkConnection(connectivityHelper)
    }

    override fun showErrorMessage(message: String) {

    }

    override fun hideErrorState() {
    }

    override fun showForceUpdateDialog() {
//        dialogHelper.showUpdateDialog(false,
//                onUpdateButton = {
//                    Toast.makeText(this, "Update clicked.", Toast.LENGTH_SHORT).show()
//
//                    //TODO remove here
//                    presenter.executeAutoLogin()
//                },
//                onLaterButton = {
//                    Toast.makeText(this, "Later clicked.", Toast.LENGTH_SHORT).show()
//                    presenter.executeAutoLogin()
//                })

        //TODO make isForce to true
//        dialogHelper.showUpdateDialog(true,
//                onUpdateButton = {
//                    Toast.makeText(this, "Update clicked.", Toast.LENGTH_SHORT).show()
//
//                    //TODO remove here
//                    presenter.executeAutoLogin()
//                })
    }

    override fun showOptionalUpdateDialog() {
//        dialogHelper.showUpdateDialog(false,
//                onUpdateButton = {
//                    Toast.makeText(this, "Update clicked.", Toast.LENGTH_SHORT).show()
//
//                    //TODO remove here
//                    presenter.executeAutoLogin()
//                },
//                onLaterButton = {
//                    Toast.makeText(this, "Later clicked.", Toast.LENGTH_SHORT).show()
//                    presenter.executeAutoLogin()
//                })
    }

}
