package com.kaloglu.boilerplate.ui.base.mvp

import android.os.Bundle
import com.kaloglu.boilerplate.ui.base.BaseActivity
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        onPresenterAttached()
    }

    override fun onDestroy() {
        onPresenterDetached()
        presenter.detachView()
        super.onDestroy()
    }

    protected open fun onPresenterAttached() {
        // Override this on child activities if needed.
    }

    protected open fun onPresenterDetached() {
        // Override this on child activities if needed.
    }
}
