package com.kaloglu.boilerplate.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Provider

@PerApplication
class ViewModelFactory @Inject
constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            requireNotNull(creators[modelClass]).get() as T
}
