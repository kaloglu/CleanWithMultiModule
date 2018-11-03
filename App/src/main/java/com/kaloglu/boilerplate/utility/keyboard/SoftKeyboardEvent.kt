package com.kaloglu.boilerplate.utility.keyboard

import android.app.Activity
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import io.reactivex.Observable
import javax.inject.Inject

@PerActivity
class SoftKeyboardEvent @Inject constructor(private val activity: Activity) {

    companion object {
        const val TAG = "SoftKeyboardEvent"
        const val THRESHOLD = 0.15
    }

    fun observe(): Observable<SoftKeyboardState> {

        val rootView = (activity.findViewById<View>(android.R.id.content) as ViewGroup?)
        val windowHeight = DisplayMetrics().let {
            activity.windowManager.defaultDisplay.getMetrics(it)
            it.heightPixels
        }

        return Observable.create<SoftKeyboardState> { emitter ->
            val listener = ViewTreeObserver.OnGlobalLayoutListener {
                if (rootView == null) {
                    Log.w(TAG, "Root view is null")
                    emitter.onNext(SoftKeyboardState.HIDE)
                    return@OnGlobalLayoutListener
                }
                val rect = Rect().apply { rootView.getWindowVisibleDisplayFrame(this) }
                val keyboardHeight = windowHeight - rect.height()

                if (keyboardHeight > (windowHeight * THRESHOLD).toInt()) {
                    emitter.onNext(SoftKeyboardState.SHOW)
                } else {
                    emitter.onNext(SoftKeyboardState.HIDE)
                }
            }

            rootView?.let {
                it.viewTreeObserver.addOnGlobalLayoutListener(listener)

                emitter.setCancellable {
                    it.viewTreeObserver.removeOnGlobalLayoutListener(listener)
                }
            }
        }.distinctUntilChanged()
    }
}
