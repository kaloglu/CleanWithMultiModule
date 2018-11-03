package com.kaloglu.boilerplate.injection.module

import com.kaloglu.boilerplate.BuildConfig
import com.kaloglu.data.cache.LocalStorage
import com.kaloglu.data.remote.auth.Token
import com.kaloglu.data.remote.service.NetworkProvider
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides


@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideToken(localStorage: LocalStorage): Token {
        return Token(localStorage)
    }

    @Provides
    @PerApplication
    fun provideServiceFactory(token: Token): NetworkProvider {
        return NetworkProvider(BuildConfig.DEBUG, BuildConfig.END_POINT, token)
    }
}