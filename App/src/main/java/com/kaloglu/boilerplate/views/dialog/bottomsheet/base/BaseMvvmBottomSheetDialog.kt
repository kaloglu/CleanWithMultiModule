package com.kaloglu.boilerplate.views.dialog.bottomsheet.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import android.view.Window
import com.kaloglu.boilerplate.viewmodel.BindableViewModel

abstract class BaseMvvmBottomSheetDialog<B : ViewDataBinding, M : BindableViewModel, R>
@JvmOverloads constructor(
        context: Context,
        private val returnModel: R? = null,
        private val onDialogSubmit: ((R) -> Unit)? = null,
        theme: Int = 0
) : BaseBottomSheetDialog(context, theme), BottomSheetDialogCallback<R?> {

    abstract val layoutId: Int
    private lateinit var dialogBinding: B
    internal lateinit var dialogModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        onDialogWindow(window)
        super.onCreate(savedInstanceState)

        dialogBinding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        dialogModel = setDialogModel()

        setContentView(dialogBinding.root)
        dialogBinding.prepareView(dialogModel)
    }

    abstract fun setDialogModel(): M

    abstract fun B.prepareView(dialogModel: M)

    open fun onDialogWindow(window: Window) = Unit

    override fun onClick(view: View) {
        onDialogSubmit(returnModel)
        dismiss()
    }

    override fun onDialogSubmit(returnModel: R?) {
        returnModel?.let {
            onDialogSubmit?.invoke(returnModel)
        }
    }

}
