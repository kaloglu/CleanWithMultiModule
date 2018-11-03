package com.kaloglu.boilerplate.injection

import com.kaloglu.boilerplate.BoilerPlateApp
import com.kaloglu.boilerplate.injection.module.*
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    PreferencesModule::class,
    ActivityBindingModule::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class,
    NetworkModule::class
])
interface ApplicationComponent : AndroidInjector<BoilerPlateApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BoilerPlateApp>()
}
