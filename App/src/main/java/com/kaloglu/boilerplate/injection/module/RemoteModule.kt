package com.kaloglu.boilerplate.injection.module

import com.kaloglu.data.remote.service.NetworkProvider
import com.kaloglu.data.repository.helloserver.remote.HelloServerService
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @PerApplication
        @Provides
        @JvmStatic
        fun provideHelloServerService(networkProvider: NetworkProvider) =
                networkProvider.create(HelloServerService::class.java)

//        @PerApplication
//        @Provides
//        @JvmStatic
//        fun provideLoginService(networkProvider: NetworkProvider) =
//                networkProvider.create(LoginService::class.java)

    }

//    @PerApplication
//    @Binds
//    abstract fun bindLoginServiceRemote(loginRemote: LoginRemoteImpl): LoginRemote

}
