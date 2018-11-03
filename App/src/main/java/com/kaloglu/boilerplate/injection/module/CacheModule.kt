package com.kaloglu.boilerplate.injection.module

import android.content.Context
import com.kaloglu.data.cache.AppCache
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {

        @PerApplication
        @Provides
        @JvmStatic
        fun provideAppCache(@ApplicationContext context: Context): AppCache {
            return AppCache(context)
        }
    }

//    @PerApplication
//    @Binds
//    abstract fun bindBulletinCache(bulletinCacheImpl: BulletinCacheImpl): BulletinCache
}
