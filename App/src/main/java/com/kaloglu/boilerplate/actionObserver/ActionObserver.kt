package com.kaloglu.boilerplate.actionObserver

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.kaloglu.databinding.views.ActionHandler

interface ActionObserver : LifecycleOwner {

    fun <T : ActionHandler> T.observe() {
        observable.observe(this@ActionObserver, Observer { action ->
            action?.getContentIfNotHandled()?.let { onNextAction(action) }
        })
    }

    fun onNextAction(action: Action<Any>)
    fun observeActionHandler() = Unit
}