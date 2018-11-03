package com.kaloglu.boilerplate

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.kaloglu.data.cache.AppCache
import com.kaloglu.boilerplate.injection.DaggerApplicationComponent
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

class BoilerPlateApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var appCache: AppCache

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .create(this)
                .inject(this)

        appCache.setupRealm()

        // Set up Crashlytics, disabled for debug builds
        val crashlyticsKit = Crashlytics.Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build()

        // Initialize Fabric with the debug-disabled crashlytics.
        Fabric.with(this, crashlyticsKit)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}