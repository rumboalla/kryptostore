package com.github.rumboalla.kryptostore.keystore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.rumboalla.kryptostore.keystore.transform.KeystoreTransform
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.transform.Transform


inline fun <reified T> encryptedKeystorePref(
    store: DataStore<Preferences>,
    name: String,
    defValue: T,
    serialization: Transform<T>
) = genPref(store, name, defValue, KeystoreTransform(serialization))

//@OptIn(ExperimentalStdlibApi::class)
//inline fun <reified T, reified K> encryptedKeystorePref2(
//    store: DataStore<Preferences>,
//    name: String,
//    defValue: T,
//    param: Any
//): GenericPreference<T> {
//    return genPref(store, name, defValue, K::class.constructors.first().call(param, typeOf<T>().javaType)!! as Transform<T>)
//}
