package com.kaloglu.boilerplate.ui.base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaloglu.boilerplate.dialogs.DialogHelper
import com.kaloglu.boilerplate.actionObserver.ActionObserver
import com.kaloglu.boilerplate.viewmodel.BaseViewModel
import com.kaloglu.boilerplate.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseMvvmFragment<VDB : ViewDataBinding, VM : BaseViewModel>
    : BaseFragment(), ActionObserver {

    //region abstract Variables
    lateinit var viewModel: VM
    lateinit var viewDataBinding: VDB
    //endregion

    //region @Inject
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var dialogHelper: DialogHelper
    //endregion

    //region override methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory)

        //TODO: Temp... Bu kısmın geliştirilmesi gerek tam bitmedi.
        savedInstanceState?.let { viewModel.restore() } ?: viewModel.update()
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        DataBindingUtil.inflate<VDB>(
                inflater,
                getResourceLayoutId(),
                container,
                false
        ).apply {
            observeActionHandler()

            setVariable(getBindingVariable(), viewModel)
            viewDataBinding = this
        }

        return viewDataBinding.root
    }

    override fun initUserInterface(rootView: View) = initUserInterface(viewDataBinding)

    abstract fun initUserInterface(viewDataBinding: VDB)

    //endregion

    //region abstract funs
    abstract fun getBindingVariable(): Int

    abstract fun getViewModelClass(): Class<VM>

    //endregion

    //region TODO: Bu kısım da dagger' a taşınabilir
    private fun provideViewModel(viewModelFactory: ViewModelFactory) =
            ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())

    //endregion

}
