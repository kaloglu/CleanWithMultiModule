package com.kaloglu.boilerplate.ui.base.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.kaloglu.boilerplate.dialogs.BaseDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseMvpDialogFragment<P : BasePresenter<*>>
    : BaseDialogFragment(), BaseView, HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: P

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        onPresenterAttached()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    protected open fun onPresenterAttached() {
        // Override this on child fragments if needed.
    }
}
