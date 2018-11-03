package com.kaloglu.data.remote.auth

import com.kaloglu.data.cache.LocalStorage

class Token(private val localStorage: LocalStorage) {

    companion object {
        @JvmStatic
        private var bearerToken: String? = null
        private var refreshToken:String? = null
    }

    init {
        bearerToken = localStorage.getBearerToken()
        refreshToken = localStorage.getRefreshToken()
    }

    fun getBearerToken(): String? = bearerToken

    fun clearBearerToken() {
        bearerToken = null
        localStorage.clearBearerToken()
    }

    fun setBearerToken(token: String) {
        bearerToken = token
        localStorage.setBearerToken(token)
    }

    fun checkBearerToken(): Boolean = !bearerToken.isNullOrEmpty()

    fun getRefreshToken(): String? = localStorage.getRefreshToken()

    fun setAccessTokenExpireDate(date:String){
        localStorage.setAccessTokenExpireDate(date)
    }

    fun clearAccessTokenExpireDate(){
        localStorage.clearAccessTokenExpireDate()
    }

}