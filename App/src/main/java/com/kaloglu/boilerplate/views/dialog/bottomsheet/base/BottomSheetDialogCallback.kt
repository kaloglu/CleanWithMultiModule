package com.kaloglu.boilerplate.views.dialog.bottomsheet.base

import android.view.View

interface BottomSheetDialogCallback<in R> : View.OnClickListener {
    fun onDialogSubmit(returnModel: R)

    override fun onClick(view: View)
}
