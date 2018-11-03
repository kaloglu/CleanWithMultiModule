package com.kaloglu.boilerplate.utility

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import com.kaloglu.boilerplate.ui.base.BaseFragment

abstract class FragmentNavigationController(
        private val fragmentManager: FragmentManager,
        @IdRes private val containerId: Int
) {

    fun showFragment(fragment: BaseFragment) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(fragment.getFragmentTag())
                .commit()
    }

    fun popBackStack(){
        fragmentManager.popBackStack()
    }

    fun clearPopStack() {
        var count = fragmentManager.backStackEntryCount
        while (count > 1) {
            fragmentManager.popBackStack()
            count--
        }
    }

}
