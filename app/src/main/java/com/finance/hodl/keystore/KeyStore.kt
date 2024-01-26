package com.finance.hodl.keystore

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class KeyStoreManager(private val context: Context) {

    private val keyStore: KeyStore by lazy {
        KeyStore.getInstance(ANDROID_KEYSTORE)
    }

    init {
        keyStore.load(null)
    }

    fun addBithumbApiKey(key: String) {
        Log.d("SUV", "add bithumbapi key as $key")
        try {
            saveKey(BITHUMB_API_KEY, key)
        } catch (e: Exception) {
            Log.d("SUV", "saveKey exception $e")
        }
    }

//    fun addBithumbPrivateKey(key:String) {
//        saveKey(BITHUMB_PRIVATE_KEY, key)
//    }

    fun getBithumbApiKey(): String? {
        try {
            return getKey(BITHUMB_API_KEY)
        } catch (e: Exception) {
            Log.d("SUV", "getKey exception $e")
        }
        return "error"
    }

//    fun getBithumbPrivateKey(): String? {
//        return getKey(BITHUMB_PRIVATE_KEY)
//    }

    private fun saveKey(alias: String, key: String) {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            setUserAuthenticationRequired(false)
            build()
        }

        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))
        val encryptedBytes = cipher.doFinal(key.toByteArray())

        val str = android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
        Log.d("SUV", "S Encripted value:$str")
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(alias, android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT))
            .apply()
    }

    private fun getSecretKey(alias: String): SecretKey {
        return keyStore.getKey(alias, null) as SecretKey
    }

    private fun getKey(alias: String): String? {
        val encryptedKeyWithIV = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(alias, null)

        Log.d("SUV", "R Encripted value:$encryptedKeyWithIV")

        if (encryptedKeyWithIV != null) {
            val encryptedBytesWithIV = Base64.decode(encryptedKeyWithIV, Base64.DEFAULT)
            val iv = encryptedBytesWithIV.sliceArray(0 until 16)
            val encryptedBytes = encryptedBytesWithIV.sliceArray(16 until encryptedBytesWithIV.size)

            val cipher = Cipher.getInstance(TRANSFORMATION)
            val key = getSecretKey(alias)
            val ivParameterSpec = IvParameterSpec(iv)
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            if (decryptedBytes.isEmpty()) {
                Log.d("SUV", "Decrypted bytes are empty")
            } else {
                Log.d("SUV", "Decrypted bytes: ${String(decryptedBytes)}")
            }
            return String(decryptedBytes)
        }

        return null
    }

    companion object {
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val TRANSFORMATION = "AES/CBC/PKCS7Padding"
        private const val PREF_NAME = "keystore_prefs"
        private const val BITHUMB_API_KEY = "bithumb_api_key"
        private const val BITHUMB_PRIVATE_KEY = "bithumb_private_key"
    }
}
