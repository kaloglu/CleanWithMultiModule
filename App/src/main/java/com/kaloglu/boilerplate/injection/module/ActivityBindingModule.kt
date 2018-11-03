package com.kaloglu.boilerplate.injection.module

import com.kaloglu.boilerplate.injection.scopes.PerActivity
import com.kaloglu.boilerplate.ui.splash.SplashActivity
import com.kaloglu.boilerplate.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributesSplashActivity(): SplashActivity

}
