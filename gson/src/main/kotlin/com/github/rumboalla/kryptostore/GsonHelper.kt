package com.github.rumboalla.kryptostore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.transform.GsonTransform
import com.google.gson.Gson
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> createGsonTransform(gson: Gson): GsonTransform<T> = GsonTransform(gson, typeOf<T>().javaType)

inline fun <reified T> gsonPref(store: DataStore<Preferences>, name: String, defValue: T, gson: Gson) =
    genPref(store, name, defValue, createGsonTransform(gson))
