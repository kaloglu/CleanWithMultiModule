package com.kaloglu.boilerplate.viewmodel

abstract class BaseViewModel  : BindableViewModel(){

    abstract fun update()
    abstract fun reset()
    abstract fun restore()
    abstract fun doGetRequest()

}
