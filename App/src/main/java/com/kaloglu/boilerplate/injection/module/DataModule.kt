package com.kaloglu.boilerplate.injection.module

import com.kaloglu.data.repository.helloserver.HelloServerDataRepository
import com.kaloglu.domain.executor.PostExecutionThread
import com.kaloglu.domain.usecase.helloserver.HelloServerRepository
import com.kaloglu.boilerplate.helper.UiThread
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @PerApplication
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @PerApplication
    @Binds
    abstract fun bindHelloServerDataRepository(helloServerDataRepository: HelloServerDataRepository): HelloServerRepository

}
