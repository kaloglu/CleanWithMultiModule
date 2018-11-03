package com.kaloglu.boilerplate.ui.base.mvp

import android.support.annotation.UiThread

interface BasePresenter<V : BaseView> {

    @UiThread
    fun attachView(view: BaseView)

    @UiThread
    fun detachView()

    /**
     * Gets the attached view. You should call [isViewAttached] to avoid exceptions.
     *
     * @return the view if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */
    @UiThread
    fun getView(): V

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false if no view is attached
     */
    @UiThread
    fun isViewAttached(): Boolean
}
