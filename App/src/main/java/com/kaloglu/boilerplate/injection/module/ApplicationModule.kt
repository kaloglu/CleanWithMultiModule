package com.kaloglu.boilerplate.injection.module

import android.content.Context
import com.kaloglu.boilerplate.BoilerPlateApp
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class ApplicationModule {

    @ApplicationContext
    @PerApplication
    @Binds
    abstract fun bindApplication(application: BoilerPlateApp): Context
}
