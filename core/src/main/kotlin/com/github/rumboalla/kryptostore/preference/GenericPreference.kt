package com.github.rumboalla.kryptostore.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import com.github.rumboalla.kryptostore.transform.Transform


open class GenericPreference<T>(
    private val store: DataStore<Preferences>,
    name: String,
    private val defValue: T,
    private val transform: Transform<T>
): Preference<T> {
    private val key = stringPreferencesKey(name)
    private val flow = store.data
        .map { it[key] ?: transform.transform(defValue) }
        .map { transform.transform(it) }

    override suspend fun get() = flow.first()
    override suspend fun set(v: T) { store.edit { it[key] = transform.transform(v) } }
    override fun flow() = flow
    override fun defaultValue(): T = defValue
}
