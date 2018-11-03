package com.kaloglu.boilerplate.ui.base.mvp

import android.support.annotation.CallSuper
import java.lang.ref.WeakReference

abstract class BaseAbstractPresenter<V : BaseView> : BasePresenter<V> {

    private var viewRef: WeakReference<V>? = null

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun attachView(view: BaseView) {
        viewRef = WeakReference(view as V)
    }

    @CallSuper
    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    /**
     * Gets the attached view. You should call [isViewAttached] to avoid exceptions.
     *
     * @return the view if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */
    override fun getView(): V {
        if (isViewAttached()) {
            return viewRef!!.get()!!
        }
        throw IllegalArgumentException()
    }

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false if no view is attached
     */
    override fun isViewAttached(): Boolean {
        return viewRef != null && viewRef!!.get() != null
    }
}
