package com.kaloglu.boilerplate.injection.module

import android.arch.lifecycle.ViewModelProvider
import com.kaloglu.boilerplate.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(CouponViewModel::class)
//    abstract fun bindsCouponViewModel(couponViewModel: CouponViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}

