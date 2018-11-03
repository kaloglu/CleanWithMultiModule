package com.kaloglu.boilerplate.injection.scopes

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FILE, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class PerChildFragment
