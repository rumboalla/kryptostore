package com.github.rumboalla.kryptostore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.transform.KxsTransform
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> createKxsTransform(
    json: Json,
    serializer: KSerializer<T>?
): KxsTransform<T> = KxsTransform(typeOf<T>().javaType, json, serializer)

inline fun <reified T> kxsPref(
    store: DataStore<Preferences>,
    name: String, defValue: T,
    json: Json = Json,
    serializer: KSerializer<T>? = null
) = genPref(store, name, defValue, createKxsTransform(json, serializer))
