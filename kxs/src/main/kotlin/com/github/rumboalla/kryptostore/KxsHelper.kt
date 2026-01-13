package com.github.rumboalla.kryptostore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.transform.KxsTransform
import kotlinx.serialization.json.Json
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> createKxsTransform(json: Json): KxsTransform<T> = KxsTransform(json, typeOf<T>().javaType)

inline fun <reified T> kxsPref(store: DataStore<Preferences>, name: String, defValue: T, json: Json = Json) =
    genPref(store, name, defValue, createKxsTransform(json))
