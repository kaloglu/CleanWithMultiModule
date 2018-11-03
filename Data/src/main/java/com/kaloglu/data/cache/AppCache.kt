package com.kaloglu.data.cache

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class AppCache(val context: Context) {

    fun setupRealm() {
        Realm.init(context)

        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)

    }

}
