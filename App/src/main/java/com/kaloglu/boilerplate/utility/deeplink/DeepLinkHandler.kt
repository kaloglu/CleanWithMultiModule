package com.kaloglu.boilerplate.utility.deeplink

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.kaloglu.boilerplate.injection.qualifier.ActivityContext
import com.kaloglu.boilerplate.injection.scopes.PerActivity
import com.kaloglu.boilerplate.ui.splash.SplashActivity
import javax.inject.Inject

@PerActivity
class DeepLinkHandler @Inject constructor(
        @ActivityContext val context: Context,
        private val uriMatcher: SampleUriMatcher) {

    private val APP_SCHEME = "sample_app"
    private val HTTP_SCHEMA = "http"
    private val HTTPS_SCHEMA = "https"
    private val DEEP_LINK_DATA = "deepLinkData"

    fun handleDeepLink(uri: Uri) {
        val intent: Intent
        intent = if (uri.scheme == APP_SCHEME) {
            val matcher = uriMatcher.match(uri)
            getBilyonerSchemeIntent(matcher)
        } else if (uri.scheme == HTTP_SCHEMA || uri.scheme == HTTPS_SCHEMA) {
            Intent() //TODO Handle this case
        } else {
            //TODO Log this case
            return
        }
        context.startActivity(intent)
    }

    private fun getBilyonerSchemeIntent(matcher: Int): Intent {
        val intent = Intent()
        val bundle = Bundle()
        when (matcher) {
            SampleUriMatcher.CodeUri.SAMPLE_CODE0.uriCode -> {
                bundle.putString("deeplink", APP_SCHEME)
                intent.setClass(context, /* TODO replace with another activity*/SplashActivity::class.java)
            }
            SampleUriMatcher.CodeUri.SAMPLE_CODE3.uriCode -> {
                bundle.putString("deeplink", APP_SCHEME)
                intent.setClass(context, /* TODO replace with another activity*/SplashActivity::class.java)
            }
            SampleUriMatcher.CodeUri.SAMPLE_CODE2.uriCode -> {
                bundle.putString("deeplink", APP_SCHEME)
                intent.setClass(context, /* TODO replace with another activity*/SplashActivity::class.java)
            }
            SampleUriMatcher.CodeUri.SAMPLE_CODE1.uriCode -> {
                bundle.putString("deeplink", APP_SCHEME)
                intent.setClass(context, /* TODO replace with another activity*/SplashActivity::class.java)
            }
            else -> intent.setClass(context, SplashActivity::class.java)//if no match is found, launch splash activity
        }
        intent.putExtra(DEEP_LINK_DATA, bundle)

        return intent
    }
}
