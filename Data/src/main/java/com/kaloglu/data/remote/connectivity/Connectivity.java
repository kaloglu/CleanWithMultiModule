package com.kaloglu.data.remote.connectivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint("MissingPermission")
public class Connectivity {

    static String pingAddress = "http://google.com/generate_204";
    static URL pingUrl = null;

    public static boolean isConnectedToNetwork(Context context) {
        boolean isConnected = false;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }

        return isConnected;
    }

    private static URL getPingUrl() throws MalformedURLException {
        if (pingUrl == null) pingUrl = new URL(pingAddress);

        return pingUrl;
    }

    public static boolean isPingSuccess() {
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection) getPingUrl().openConnection();
            httpUrlConnection.setConnectTimeout(6000);
            httpUrlConnection.setReadTimeout(6000);
            httpUrlConnection.connect();

            return httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT;
        } catch (Exception e) {
            Log.e("Connectivity", "Ping failed: " + pingAddress);
            e.printStackTrace();
            return false;
        }
    }

}
