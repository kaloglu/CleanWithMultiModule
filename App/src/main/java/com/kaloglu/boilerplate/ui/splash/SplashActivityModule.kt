package com.kaloglu.boilerplate.ui.splash

import com.kaloglu.data.cache.LocalStorage
import com.kaloglu.domain.usecase.helloserver.GetHelloServer
import com.kaloglu.boilerplate.helper.DeviceInfoHelper
import com.kaloglu.boilerplate.injection.module.ActivityModule
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import com.kaloglu.boilerplate.ui.base.BaseActivity
import com.kaloglu.boilerplate.utility.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
abstract class SplashActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun providePresenter(
                getHelloServer: GetHelloServer,
//                getAutoLogin: GetAutoLogin,
                deviceInfoHelper: DeviceInfoHelper,
                localStorage: LocalStorage,
                navigator: Navigator
        ): SplashContract.Presenter {
            return SplashPresenter(getHelloServer, /*getAutoLogin,*/ deviceInfoHelper, localStorage, navigator)
        }
    }

    @PerActivity
    @Binds
    abstract fun bindBaseActivity(activity: SplashActivity): BaseActivity
}
