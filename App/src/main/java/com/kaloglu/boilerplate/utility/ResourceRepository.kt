package com.kaloglu.boilerplate.utility

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import com.kaloglu.boilerplate.injection.qualifier.ActivityContext
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import javax.inject.Inject

@PerActivity
class ResourceRepository @Inject constructor(@ActivityContext private val context: Context) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int): String {
        return context.resources.getQuantityString(resId, quantity)
    }

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any): String {
        return context.resources.getQuantityString(resId, quantity, *formatArgs)
    }

    fun getInteger(@IntegerRes resId: Int): Int {
        return context.resources.getInteger(resId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {

        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            context.resources.getDrawable(resId, context.theme)
        } else ContextCompat.getDrawable(context, resId)

    }

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun getDimension(@DimenRes resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    @Px
    fun getDimensionPixelSize(@DimenRes resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }

    fun getRawUri(@RawRes rawRes: Int): Uri {
        return Uri.parse("android.resource://${context.packageName}/$rawRes")
    }
}
