package com.kaloglu.databinding.views

import android.arch.lifecycle.MutableLiveData
import com.kaloglu.boilerplate.actionObserver.Action

/**
 *  @see: https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 *  Recommended: Use an Event wrapper
 *  */
abstract class ActionHandler {
    val observable by lazy { MutableLiveData<Action<Any>>() }

    fun onAction(doAction: Action<Any>) {
        observable.value = doAction
    }


    //region samples SHOULD ADD EXTENDED or SUB object!
//    @JvmStatic
//    fun saveCoupon(view: View) = onAction(ShowDialog.SaveCoupon(view))
//
//    @JvmStatic
//    fun shareCoupon(view: View) = onAction(ShowDialog.ShareCoupon(view))
//
//    @JvmStatic
//    fun showPlaySuccessDialog(view: View) = onAction(ShowDialog.PlaySuccess(view))
//
//    @JvmStatic
//    fun collapseCoupon(view: View) = onAction(CollapseCoupon())
//
//    @JvmStatic
//    fun clearCoupon(view: View) = onAction(ClearCoupon())
    //endregion
}