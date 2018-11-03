package com.kaloglu.boilerplate.dialogs

import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View
import com.kaloglu.boilerplate.R
import com.kaloglu.boilerplate.utility.extensions.backgroundDrawable
import com.kaloglu.boilerplate.utility.extensions.showView
import com.kaloglu.boilerplate.utility.extensions.src
import com.kaloglu.boilerplate.utility.extensions.text
import com.kaloglu.boilerplate.utility.extensions.textColor
import com.kaloglu.boilerplate.utility.extensions.textSize
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.layout_dialog_alert.*

@FragmentWithArgs
open class AlertDialogFragment : BaseDialogFragment() {

    //region FragmentWithArgs
    @Arg(required = false)
    var isDismissable: Boolean = true

    @Arg(required = false)
    @DrawableRes
    var iconTitleResId: Int? = null

    @Arg(required = false)
    @ColorRes
    var iconTitleColorResId: Int? = null

    @Arg(required = false)
    var title: String? = null

    @Arg(required = false)
    @StringRes
    var titleResId: Int? = null

    @Arg(required = false)
    @DimenRes
    var titleTextSize: Int? = null

    @Arg(required = false)
    @ColorRes
    var titleColorResId: Int? = null

    @Arg(required = false)
    var message: String? = null

    @Arg(required = false)
    @StringRes
    var messageResId: Int? = null

    @Arg(required = false)
    var positiveButtonText: String? = null

    @Arg(required = false)
    @StringRes
    var positiveButtonTextResId: Int? = null

    @Arg(required = false)
    @ColorRes
    var positiveButtonTextColorResId: Int? = null

    @Arg(required = false)
    @DrawableRes
    var positiveButtonBackgroundResId: Int? = null

    @Arg(required = false)
    var neutralButtonText: String? = null

    @Arg(required = false)
    @StringRes
    var neutralButtonTextResId: Int? = null

    @Arg(required = false)
    @ColorRes
    var neutralButtonTextColorResId: Int? = null

    @Arg(required = false)
    @DrawableRes
    var neutralButtonBackgroundResId: Int? = null

    //endregion

    var onPositiveButtonClick: (() -> Unit)? = null

    var onNeutralButtonClick: (() -> Unit)? = null

    override fun getResourceLayoutId() = R.layout.layout_dialog_alert

    override fun initUserInterface(dialogView: View) {
        imageViewTitleIcon.src(iconTitleResId, iconTitleColorResId)

        textViewTitle.text(title, titleResId)
        textViewTitle.textColor(titleColorResId)
        textViewTitle.textSize(titleTextSize)

        textViewMessage.text(message, messageResId)

        buttonPositive.let {
            it.text(positiveButtonText, positiveButtonTextResId)
            it.textColor(positiveButtonTextColorResId)
            it.backgroundDrawable(positiveButtonBackgroundResId)
            it.setOnClickListener {
                onPositiveButtonClick?.invoke()
                dismiss()
            }
        }
        buttonNeutral.let {
            it.text(neutralButtonText, neutralButtonTextResId)
            it.textColor(neutralButtonTextColorResId)
            it.backgroundDrawable(neutralButtonBackgroundResId)
            it.setOnClickListener {
                onNeutralButtonClick?.invoke()
                dismiss()
            }
        }

        imageViewDismiss.showView(isDismissable)
        imageViewDismiss.setOnClickListener {
            dismiss()
        }
    }

}
