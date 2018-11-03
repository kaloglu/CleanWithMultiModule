package com.kaloglu.boilerplate.utility.binding

import android.databinding.BindingConversion
import android.view.View

@BindingConversion
fun booleanToVisibilty(isVisible: Boolean) =
        when {
            isVisible -> View.VISIBLE
            else -> View.GONE
        }
