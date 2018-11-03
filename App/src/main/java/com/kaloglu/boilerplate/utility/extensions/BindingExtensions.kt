@file:JvmName("BindingUtil")

package com.kaloglu.boilerplate.utility.extensions

import android.databinding.DataBindingUtil
import android.databinding.InverseMethod
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * if you don't have inflater
 *
 * parent.inflateBinding(layoutId = R.layout#Name)
 *
 * @receiver ViewGroup
 *
 * @param layoutId @LayoutRes
 *
 * @return ViewDataBinding
 *
 * */
fun ViewGroup.inflateBinding(@LayoutRes layoutId: Int): ViewDataBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, false)

@InverseMethod("stringToInt")
fun Int.intToString(): String {
    return toString()
}

fun String.stringToInt(): Int {
    if (this == "")
        return 0
    return trim().toInt()
}