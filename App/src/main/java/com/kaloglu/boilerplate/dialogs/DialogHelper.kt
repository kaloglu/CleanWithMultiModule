package com.kaloglu.boilerplate.dialogs

import android.support.v4.app.FragmentManager
import com.kaloglu.boilerplate.R
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import com.kaloglu.boilerplate.utility.extensions.findFragment
import javax.inject.Inject

@PerActivity
class DialogHelper @Inject constructor(private val supportFragmentManager: FragmentManager) {

    companion object {
        private const val SUCCESS_COUPON_DIALOG = "SUCCESS_COUPON_DIALOG"
        private const val SUCCESS_DIALOG = "SUCCESS_DIALOG"
        private const val SHARED_COUPON_DIALOG = "SHARED_COUPON_DIALOG"
        private const val SAVED_COUPON_DIALOG = "SAVED_COUPON_DIALOG"
        private const val PROGRESS_DIALOG = "PROGRESS_DIALOG"
        private const val CANCEL_REGISTER_DIALOG = "CANCEL_REGISTER_DIALOG"
        private const val DATE_PICKER_DIALOG = "DATE_PICKER_DIALOG"
        private const val WARNING_DIALOG = "WARNING_DIALOG"
        private const val ERROR_DIALOG = "ERROR_DIALOG"
        private const val NOT_ENOUGH_BALANCE_DIALOG = "NOT_ENOUGH_BALANCE_DIALOG"
        private const val EVENT_INFO_DIALOG = "EVENT_INFO_DIALOG"
        private const val POOLS_POPULER_DIALOG = "POOLS_POPULER_DIALOG"
        private const val POOLS_CHANCE_DIALOG = "POOLS_CHANCE_DIALOG"
        private const val POOLS_SUCCESS_COUPON_DIALOG = "POOLS_SUCCESS_COUPON_DIALOG"
        private const val POOLS_NOT_ENOUGH_BALANCE_DIALOG = "POOLS_NOT_ENOUGH_BALANCE_DIALOG"
        private const val POOLS_COUPON_DETAIL_DIALOG = "POOLS_COUPON_DETAIL_DIALOG"
        private const val POOLS_CANCEL_COUPON_DIALOG = "POOLS_CANCEL_COUPON_DIALOG"
        private const val LOTTERY_SERIES_TICKET_DIALOG = "LOTTERY_SERIES_TICKET_DIALOG"
        private const val LOTTERY_PURCHASED_SERIES_TICKET_DIALOG = "LOTTERY_PURCHASED_SERIES_TICKET_DIALOG"
        private const val APP_UPDATE_DIALOG = "APP_UPDATE_DIALOG"
    }

    private val String.dismissDialog: () -> Unit
        get() = {
            findFragment<BaseDialogFragment>(this)?.dismiss()
        }
//region sample
//    fun showSavedCouponDialog(onPositiveButton: (() -> Unit)?) {
//        val tag = SAVED_COUPON_DIALOG
//        AlertDialogFragmentBuilder()
//                .messageResId(R.string.succeed_coupon_share_message)
//                .iconTitleColorResId(R.color.jungle_green)
//                .iconTitleResId(R.drawable.ic_like)
//                .positiveButtonTextResId(R.string.go_bulletin)
//                .positiveButtonBackgroundResId(R.drawable.btn_half_round_green_gradient)
//                .build().apply {
//                    isCancelable = false
//                    onPositiveButtonClick = onPositiveButton ?: tag.dismissDialog
//
//                    show(supportFragmentManager, tag)
//                }
//
//    }
//
//    fun showSaveCouponDialog(onPositiveButton: ((prompt: String) -> Unit)?) {
//
//        val tag = SAVED_COUPON_DIALOG
//
//        SaveCouponDialogFragmentBuilder()
//                .build().apply {
//
//                    onPositiveButtonClick = onPositiveButton
//
//                    show(supportFragmentManager, tag)
//                }
//
//    }
    //endregion

    fun showErrorDialog(title: String, message: String) {
        val warningDialog = AlertDialogFragmentBuilder()
                .iconTitleColorResId(R.color.pink_red)
//                .iconTitleResId(R.drawable.ic_close_delete)
                .title(title)
                .message(message)
                .build()

        with(warningDialog) {
            warningDialog.isCancelable = false
            show(supportFragmentManager, ERROR_DIALOG)
        }
    }

    private inline fun <reified T : BaseDialogFragment> findFragment(tag: String): T? =
            supportFragmentManager.findFragment(tag) as T?

}
