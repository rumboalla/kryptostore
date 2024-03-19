package com.github.rumboalla.kryptostore.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


open class PrimitivePreference<T>(
    private val store: DataStore<Preferences>,
    private val key: Preferences.Key<T>,
    private val defValue: T
): Preference<T> {
    private val flow = store.data.map { it[key] ?: defValue }

    override suspend fun get() = flow.first()
    override suspend fun set(v: T) { store.edit { it[key] = v } }
    override fun flow() = flow
}
