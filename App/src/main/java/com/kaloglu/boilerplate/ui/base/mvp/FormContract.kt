package com.kaloglu.boilerplate.ui.base.mvp

import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.view.View

interface FormContract {
    interface FormView : BaseView {

        var submitButtonView: View

        fun setSubmitButton(view: View, function: (View) -> Unit = {}) {
            submitButtonView = view
            setSubmitButtonOnClickListener(function)
        }

        private fun setSubmitButtonOnClickListener(function: (View) -> Unit = {}) {
            submitButtonView.setOnClickListener {
                if (submitButtonView.isEnabled) function(it)
            }
        }

        fun getSubmitButton() = submitButtonView

        fun showInfoAlert(@StringRes stringResId: Int, @ColorRes colorResId: Int)


    }

    interface FormPresenter<V : FormView> : BasePresenter<V> {

        private fun getSubmitButton() = getView().getSubmitButton()

        fun setSubmitButtonEnabledIfPossible() = setSubmitButtonEnabled(getSubmitButton())

        fun submitForm() {
            if (canSubmitForm() && isFormValid()) onSubmitForm()
        }

        private fun setSubmitButtonEnabled(submitButton: View) {
            submitButton.isEnabled = canSubmitForm()
        }

        fun isFormValid(): Boolean

        fun canSubmitForm(): Boolean

        fun onSubmitForm()

        fun showSuccessDialog()
    }
}
