package com.kaloglu.data.remote.service

import android.util.Log
import com.kaloglu.data.remote.auth.AuthorizationException
import com.kaloglu.data.remote.auth.Token
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class NetworkProvider(isDebug: Boolean,
                      endpoint: String,
                      private val token: Token) {

    companion object {
        const val CONNECT_TIME_OUT = 120L
        const val READ_TIME_OUT = 120L
    }

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(endpoint)
                .client(makeOkHttpClient(isDebug))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    private fun makeHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request()
                    .newBuilder()
                    .addHeader("HandshakeContent-AdapterItemType", "application/json")

            if (token.checkBearerToken()) {
                requestBuilder.addHeader("Authorization", "Bearer " + token.getBearerToken())
            } else {
                //TODO burada log basilacak.....
                Log.e("Authorization", "Bearer token not found")
            }
            it.proceed(requestBuilder.build())
        }
    }

    private fun makeAuthInterceptor(): Interceptor {//TODO login entegre edildiğinde düzeltilecek
        return Interceptor {
            val request = it.request()
            var response = it.proceed(request)

            if (response.code() == 401) {
                token.clearBearerToken()
                token.clearAccessTokenExpireDate()
                retryWithFreshToken(request, it)
                response = it.proceed(request)
            }
            response
        }
    }

    @Throws(IOException::class)
    private fun retryWithFreshToken(request: Request, chain: Interceptor.Chain): Response {

        if (refreshToken()) {
            request.newBuilder().addHeader("Authorization", "Bearer " + token.getBearerToken())

            return chain.proceed(request)
        }
        throw AuthorizationException()
    }

    @Throws(IOException::class)
    private fun refreshToken(): Boolean {//TODO login entegre edildiğinde düzeltilecek.

        /*val tokenRequest = RefreshTokenRequest(token.getRefreshToken()!!)

        val response = retrofit.create(AuthService::class.java)
                .refreshToken(tokenRequest)
                .execute()

        if (response.isSuccessful) {
            // refreshToken request succeed, new token generated
            val refreshToken = response.body()
            // save the new token
            token.setBearerToken(refreshToken!!.accessToken)
            //save the new access token expire date
            token.setAccessTokenExpireDate(refreshToken.accessTokenExpireDate)
            return true
        }*/
        return false
    }

    private fun makeOkHttpClient(isDebug: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(makeAuthInterceptor())
                .addInterceptor(makeHeaderInterceptor())
                .addInterceptor(makeLoggingInterceptor(isDebug))
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    fun <S> create(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}