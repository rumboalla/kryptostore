package com.github.rumboalla.kryptostore.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.github.rumboalla.kryptostore.transform.Transform


fun intPref(store: DataStore<Preferences>, name: String, defValue: Int) =
    PrimitivePreference(store, intPreferencesKey(name), defValue)

fun stringPref(store: DataStore<Preferences>, name: String, defValue: String) =
    PrimitivePreference(store, stringPreferencesKey(name), defValue)

fun booleanPref(store: DataStore<Preferences>, name: String, defValue: Boolean) =
    PrimitivePreference(store, booleanPreferencesKey(name), defValue)

fun doublePref(store: DataStore<Preferences>, name: String, defValue: Double) =
    PrimitivePreference(store, doublePreferencesKey(name), defValue)

fun floatPref(store: DataStore<Preferences>, name: String, defValue: Float) =
    PrimitivePreference(store, floatPreferencesKey(name), defValue)

fun stringSetPref(store: DataStore<Preferences>, name: String, defValue: Set<String>) =
    PrimitivePreference(store, stringSetPreferencesKey(name), defValue)

fun <T> genPref(store: DataStore<Preferences>, name: String, defValue: T, transform: Transform<T>) =
    GenericPreference(store, name, defValue, transform)
