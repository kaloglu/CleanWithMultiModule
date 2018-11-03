package com.kaloglu.boilerplate.helper

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.kaloglu.boilerplate.BuildConfig
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import javax.inject.Inject


@PerApplication
class DeviceInfoHelper @Inject constructor(
        @ApplicationContext private val context: Context) {

    companion object {
        const val DEVICE_TYPE = "Android"
        const val LANG_KEY = "TR"
    }

    val apiLevel
        get() = Build.VERSION.SDK_INT

    val appVersion
        get() = BuildConfig.VERSION_NAME

    val density
        get() = context.resources.displayMetrics.density.toString()

    val deviceModel
        get() = Build.MANUFACTURER + " " + Build.MODEL

    val deviceType
        get() = DEVICE_TYPE

    val langKey
        get() = LANG_KEY

    val size
        get() = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

    val systemVersion
        get() = Build.VERSION.RELEASE

}