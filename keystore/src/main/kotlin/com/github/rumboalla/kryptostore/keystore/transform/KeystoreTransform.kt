package com.github.rumboalla.kryptostore.keystore.transform

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.github.rumboalla.kryptostore.transform.Transform
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec


class KeystoreTransform<T>(
    private val serialization: Transform<T>
): Transform<T> {

    companion object {
        private const val ALIAS = "KryptoStoreKeyAlias"
        private const val PROVIDER = "AndroidKeyStore"
        private const val CIPHER = "AES/GCM/NoPadding"
        private const val TAG = 128
        private const val IV = 12
        private const val KEY_SIZE = 256
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
        private const val PADDING_MODE = KeyProperties.ENCRYPTION_PADDING_NONE
        private const val PURPOSE = KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT
    }

    private val key by lazy {
        val keyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }
        if (!keyStore.containsAlias(ALIAS)) {
            val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER)
            val builder =  KeyGenParameterSpec.Builder(ALIAS, PURPOSE)
                .setBlockModes(BLOCK_MODE)
                .setEncryptionPaddings(PADDING_MODE)
                .setKeySize(KEY_SIZE)
            generator.init(builder.build())
            generator.generateKey()
        }
        keyStore.getKey(ALIAS, null)
    }

    override fun transform(t: T): String {
        val cipher = Cipher.getInstance(CIPHER)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return Base64.encodeToString(cipher.iv + cipher.doFinal(serialization.transform(t).toByteArray()), Base64.NO_WRAP)
    }

    override fun transform(t: String): T {
        val cipher = Cipher.getInstance(CIPHER)
        val data = Base64.decode(t, Base64.NO_WRAP)
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(TAG, data, 0, IV))
        return serialization.transform(cipher.doFinal(data, IV, data.size - IV).toString(Charsets.UTF_8))
    }

}
