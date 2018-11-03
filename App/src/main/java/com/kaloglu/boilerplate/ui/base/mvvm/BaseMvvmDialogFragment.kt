package com.kaloglu.boilerplate.ui.base.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.kaloglu.boilerplate.dialogs.BaseDialogFragment
import com.kaloglu.boilerplate.actionObserver.ActionObserver
import com.kaloglu.boilerplate.viewmodel.BaseViewModel
import com.kaloglu.boilerplate.viewmodel.ViewModelFactory
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseMvvmDialogFragment<VDB : ViewDataBinding, VM : BaseViewModel>
    : BaseDialogFragment(), HasSupportFragmentInjector, ActionObserver {

    //region abstract Variables
    lateinit var viewModel: VM
    lateinit var viewDataBinding: VDB
    //endregion

    //region @Inject
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    //endregion

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    //region override methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory)

        //TODO: Temp... Bu kısmın geliştirilmesi gerek tam bitmedi.
        savedInstanceState?.let { viewModel.restore() } ?: viewModel.update()
    }

    override fun inflateView(layoutInflater: LayoutInflater, container: ViewGroup?): View {
        DataBindingUtil.inflate<VDB>(
                layoutInflater,
                getResourceLayoutId(),
                container,
                false
        ).apply {
            observeActionHandler()

            setVariable(getBindingVariable(), viewModel)
            viewDataBinding = this
        }

        isCancelable = true

        return viewDataBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
           super.onCreateDialog(savedInstanceState).apply { onDialogWindow(window) }

    override fun supportFragmentInjector() = childFragmentInjector

    override fun initUserInterface(dialogView: View) = initUserInterface(viewDataBinding)

    abstract fun initUserInterface(viewDataBinding: VDB)

    override fun onDialogWindow(window: Window) {
        super.onDialogWindow(window)
        window.setGravity(Gravity.BOTTOM)
        window.attributes.windowAnimations = 0
    }

    //endregion

    //region abstract funs
    abstract fun getBindingVariable(): Int

    abstract fun getViewModelClass(): Class<VM>

    //endregion

    //region TODO: Bu kısım da dagger' a taşınabilir
    private fun provideViewModel(viewModelFactory: ViewModelFactory) =
            ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())

    //endregion


    //region sample SHOULD ADD EXTENDED or SUB class!
//    //region ActionPresenter
//    override fun observeActionHandler() = BetslipActionHandler.observe()
//
//    override fun onNextAction(action: Action<Any>) {
//        //samples
//        when (action) {
//            is CollapseCoupon -> collapseCoupon()
//            is ClearCoupon -> clearCoupon()
//
//            is ShowDialog.SaveCoupon -> showSaveCouponDialog()
//            is ShowDialog.ShareCoupon -> showShareCouponDialog()
//        }
//    }
//
//    //region Presenter Methods (samples)
//    private fun showSaveCouponDialog() = Unit
////            dialogHelper.showSaveCouponDialog {
////        dialogHelper.showSavedCouponDialog { clearCoupon() }
////    }
//
//    private fun showShareCouponDialog() = Unit
////            dialogHelper.showSharedCouponDialog {
////        collapseCoupon()
////    }
//
//    private fun collapseCoupon() = dismiss()
//
//    private fun clearCoupon() {
////        BetslipManager.clear()
//        collapseCoupon()
//    }
//
//    //endregion
//
//    //endregion
    //endregion
}
