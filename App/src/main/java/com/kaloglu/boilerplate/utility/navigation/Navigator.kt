package com.kaloglu.boilerplate.utility.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import javax.inject.Inject

@PerActivity
class Navigator @Inject constructor(private val activity: Activity) {

    fun finishCurrentActivity(): NavigationCreator {
        return NavigationCreator(activity).finishThis()
    }

    fun openExternalUrl(url: String): NavigationCreator {
        return NavigationCreator(activity)
                .intent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun toMainActivity(): NavigationCreator {
        return NavigationCreator(activity)
//                .intent(MainActivity.newIntent(activity))
    }

}
