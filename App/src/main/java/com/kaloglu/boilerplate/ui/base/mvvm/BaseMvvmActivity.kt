package com.kaloglu.boilerplate.ui.base.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import com.kaloglu.boilerplate.ui.base.BaseActivity
import com.kaloglu.boilerplate.ui.base.BaseMvvmFragment
import com.kaloglu.boilerplate.viewmodel.BaseViewModel
import com.kaloglu.boilerplate.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseMvvmActivity<VDB : ViewDataBinding, VM : BaseViewModel> : BaseActivity() {

    lateinit var viewDataBinding: VDB
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private fun provideViewModel(viewModelFactory: ViewModelFactory) =
            ViewModelProviders.of(this, viewModelFactory).get(getViewModel())

    override fun setContentView() {
        viewDataBinding = DataBindingUtil.setContentView(this, getContentResourceId())
        viewModel = provideViewModel(viewModelFactory)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    override fun initUserInterface() {
        viewDataBinding.onBind()
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun VDB.onBind()

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    override fun getContainedFragment() = getContainedMvvmFragment()

    abstract fun getContainedMvvmFragment(): BaseMvvmFragment<*, *>
}
