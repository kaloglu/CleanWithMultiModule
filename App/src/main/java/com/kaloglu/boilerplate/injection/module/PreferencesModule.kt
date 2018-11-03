package com.kaloglu.boilerplate.injection.module

import android.content.Context
import com.kaloglu.data.cache.LocalStorage
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @PerApplication
    @Provides
    fun providesSharedDataPreferences(@ApplicationContext context: Context): LocalStorage {
        return LocalStorage(context)
    }
}
