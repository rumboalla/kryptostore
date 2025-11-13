package com.github.rumboalla.kryptostore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.transform.KxsTransform
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> createKxsTransform(): KxsTransform<T> = KxsTransform(typeOf<T>().javaType)

inline fun <reified T> kxsPref(store: DataStore<Preferences>, name: String, defValue: T) =
    genPref(store, name, defValue, createKxsTransform())
