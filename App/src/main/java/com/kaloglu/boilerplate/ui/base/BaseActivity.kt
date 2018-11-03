package com.kaloglu.boilerplate.ui.base

import android.os.Bundle
import com.kaloglu.boilerplate.R
import com.kaloglu.boilerplate.helper.ConnectivityHelper
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var connectivityHelper: ConnectivityHelper

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        compositeDisposable = CompositeDisposable()

        initUserInterface()

        if (savedInstanceState == null) {

            val fragment = getContainedFragment()

            if (fragment != null) {

                val tag = fragment.getFragmentTag()

                supportFragmentManager.beginTransaction()
                        .add(getBaseFrameLayoutId(), fragment, tag)
                        .commit()
            }
        }
    }

    //Bu method'un ismi değişebilir
    protected open fun setContentView() {
        setContentView(getContentResourceId())
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    /**
     * Initialize UI content elements.
     */
    protected abstract fun initUserInterface()

    /**
     * Method to get activity's UI content layout resource id.
     * if overwritten [getBaseFrameLayoutId][.getBaseFrameLayoutId] must also be implemented.
     *
     * @return The activity's content's resource id
     */
    protected open fun getContentResourceId(): Int = R.layout.activity_base

    /**
     * Method to get activity's UI content frame layout resource id.
     *
     * @return The activity's frame layout resource id
     */
    protected open fun getBaseFrameLayoutId(): Int = R.id.activity_base_fragment_container

    /**
     * Get initial fragment instance.
     *
     * @return Fragment
     */
    protected abstract fun getContainedFragment(): BaseFragment?
}
