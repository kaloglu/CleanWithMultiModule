package com.kaloglu.databinding.model

import android.databinding.Bindable
import com.kaloglu.boilerplate.viewmodel.BindableViewModel

abstract class RecyclerItem : BindableViewModel() {

    @get:Bindable
    abstract var layoutId: Int

    @get:Bindable
    open val parent: RecyclerItem? = null

}
