package com.kaloglu.boilerplate.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class NetworkHelper @Inject constructor(@ApplicationContext context: Context) {

    private val networkInfo: NetworkInfo

    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkInfo = connectivityManager.activeNetworkInfo
    }

    /**
     * Convenience method for checking network connectivity.
     *
     * @return true if network connectivity exists or is in the process of being established, false otherwise.
     */
    fun isConnectedOrConnecting(): Boolean {
        return networkInfo.isConnectedOrConnecting
    }

    /**
     * Convenience method for checking if there is any connectivity to a Wifi.
     *
     * @return true if there is connectivity to Wifi.
     */
    fun isConnectedToWifi(): Boolean {
        return networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Convenience method for checking if there is any connectivity to a mobile network.
     *
     * @return true if there is connectivity to mobile network.
     */
    fun isConnectedToMobile(): Boolean {
        return networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_MOBILE
    }
}
