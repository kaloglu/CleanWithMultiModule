package com.kaloglu.boilerplate.helper.fingerprints

import android.hardware.fingerprint.FingerprintManager

interface FingerPrintsCallback {

    fun onAuthSuccess(cryptoObject: FingerprintManager.CryptoObject)

    fun onAuthFailed()

}
