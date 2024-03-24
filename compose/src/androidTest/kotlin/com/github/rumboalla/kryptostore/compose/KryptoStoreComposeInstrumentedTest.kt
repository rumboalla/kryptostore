package com.github.rumboalla.kryptostore.compose

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith


private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "test")

@RunWith(AndroidJUnit4::class)
class KryptoStoreGsonInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        // Clears the datastore
        runBlocking { context.store.edit { it.clear() } }
    }

    @Test
    fun testCompose() = runBlocking {

    }

}
