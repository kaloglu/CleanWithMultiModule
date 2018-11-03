package com.kaloglu.boilerplate.dialogs

import android.support.v4.app.FragmentManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.kaloglu.boilerplate.R
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.layout_dialog_progress.*

@FragmentWithArgs
class ProgressDialogFragment : BaseDialogFragment() {

    override fun getResourceLayoutId()  = R.layout.layout_dialog_progress

    private var showing: Boolean = false

    override fun initUserInterface(dialogView: View) = Unit

    override fun onStart() {
        super.onStart()
        loadingIndicatorView!!.visibility = VISIBLE
    }

    override fun onPause() {
        loadingIndicatorView!!.visibility = GONE
        super.onPause()
    }

    override fun show(manager: FragmentManager, tag: String) {

        if (showing || isAdded) {
            showing = true
            return
        }

        super.show(manager, tag)

        showing = true
    }

    override fun dismiss() {

        if (!showing || fragmentManager == null) {
            showing = false
            return
        }

        super.dismiss()

        showing = false
    }

    override fun onDestroy() {
        super.dismiss()
        super.onDestroy()
    }
}
