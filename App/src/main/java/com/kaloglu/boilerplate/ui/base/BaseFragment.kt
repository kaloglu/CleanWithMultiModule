package com.kaloglu.boilerplate.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaloglu.boilerplate.helper.ConnectivityHelper
import com.hannesdorfmann.fragmentargs.FragmentArgs
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var connectivityHelper: ConnectivityHelper

    lateinit var compositeDisposable: CompositeDisposable

    override fun onAttach(context: Context) {
        FragmentArgs.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflateView(inflater, container)

        compositeDisposable = CompositeDisposable()

        return rootView
    }

    open fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View =
            inflater.inflate(getResourceLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInterface(view)
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
        super.onDestroyView()
    }

    /**
     * Get fragment's UI content layout resource id.
     *
     * @return The fragment's root view's resource id
     */
    protected abstract fun getResourceLayoutId(): Int

    /**
     * Initialize UI content elements.
     *
     * @param rootView The fragment's root view
     */
    protected abstract fun initUserInterface(rootView: View)

    /**
     * Finds and returns the frame layout's id which holds the fragment.
     *
     * @return id as int
     */
    fun getContainerFrameLayoutId(): Int? {
        return (view?.parent as ViewGroup).id
    }

    fun getFragmentTag(): String {
        return this.javaClass.simpleName
    }

    open fun onBackPressed() {

    }
}
