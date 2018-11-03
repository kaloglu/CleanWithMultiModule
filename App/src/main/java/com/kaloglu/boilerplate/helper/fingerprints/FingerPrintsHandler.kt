package com.kaloglu.boilerplate.helper.fingerprints

import android.annotation.TargetApi
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import com.kaloglu.boilerplate.injection.qualifier.ApplicationContext
import com.kaloglu.boilerplate.injection.scopes.PerApplication
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.inject.Inject

@PerApplication
class FingerPrintsHandler @Inject constructor(@ApplicationContext val context: Context) {

    private var mKeyStore: KeyStore? = null

    private var mCipher: Cipher? = null

    private var mCancellationSignal: CancellationSignal? = null

    var isScanning: Boolean = false

    private lateinit var callback: FingerPrintsCallback

    fun setCallback(fingerPrintCallback: FingerPrintsCallback) {
        callback = fingerPrintCallback
    }

    private val cryptoObject: FingerprintManager.CryptoObject?
        @TargetApi(23)
        get() = if (cipherInit()) FingerprintManager.CryptoObject(mCipher!!) else null

    fun isHardwareSupportOrMarshmallow(): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && fingerprintManager.isHardwareDetected
    }

    fun hasFingerPrints(): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return fingerprintManager.hasEnrolledFingerprints()
    }

    @TargetApi(23)
    private fun generateKey(): Boolean {
        mKeyStore = null
        val keyGenerator: KeyGenerator

        //Get the instance of the key store.
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore")
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        } catch (e: NoSuchAlgorithmException) {
            return false
        } catch (e: NoSuchProviderException) {
            return false
        } catch (e: KeyStoreException) {
            return false
        }

        //generate key.
        try {
            mKeyStore!!.load(null)
            keyGenerator.init(KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build())
            keyGenerator.generateKey()

            return true
        } catch (e: NoSuchAlgorithmException) {
            return false
        } catch (e: InvalidAlgorithmParameterException) {
            return false
        } catch (e: CertificateException) {
            return false
        } catch (e: IOException) {
            return false
        }

    }

    @TargetApi(23)
    private fun cipherInit(): Boolean {
        val isKeyGenerated = generateKey()

        if (!isKeyGenerated) {
            callback.onAuthFailed()
            return false
        }
        try {
            mCipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        } catch (e: NoSuchAlgorithmException) {
            callback.onAuthFailed()
            return false
        } catch (e: NoSuchPaddingException) {
            callback.onAuthFailed()
            return false
        }

        return try {
            mKeyStore!!.load(null)
            val key = mKeyStore!!.getKey(KEY_NAME, null) as SecretKey
            mCipher!!.init(Cipher.ENCRYPT_MODE, key)
            true
        } catch (e: Exception) {
            callback.onAuthFailed()
            false
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    fun startAuthentication() {
        if (isScanning) stopAuthentication()
        val fingerprintManager = context.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        val cryptoObject = cryptoObject
        if (cryptoObject == null) {
            callback.onAuthFailed()
        } else {
            mCancellationSignal = CancellationSignal()
            fingerprintManager.authenticate(cryptoObject,
                    mCancellationSignal,
                    0,
                    object : FingerprintManager.AuthenticationCallback() {
                        override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
                            callback.onAuthFailed()
                        }

                        override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
                            callback.onAuthFailed()
                        }

                        override fun onAuthenticationFailed() {
                            callback.onAuthFailed()
                        }

                        override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
                            callback.onAuthSuccess(result.cryptoObject)
                        }
                    }, null)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun stopAuthentication() {
        if (mCancellationSignal != null) {
            isScanning = true
            mCancellationSignal!!.cancel()
            mCancellationSignal = null
        }
    }

    companion object {
        private val KEY_NAME = UUID.randomUUID().toString()
    }

}