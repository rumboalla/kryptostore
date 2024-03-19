package com.github.rumboalla.kryptostore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.rumboalla.kryptostore.annotation.DangerousApi
import com.github.rumboalla.kryptostore.preference.booleanPref
import com.github.rumboalla.kryptostore.preference.doublePref
import com.github.rumboalla.kryptostore.preference.floatPref
import com.github.rumboalla.kryptostore.preference.genPref
import com.github.rumboalla.kryptostore.preference.intPref
import com.github.rumboalla.kryptostore.preference.stringPref
import com.github.rumboalla.kryptostore.preference.stringSetPref
import com.github.rumboalla.kryptostore.transform.Transform
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith


private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "test")

@RunWith(AndroidJUnit4::class)
class KryptoStoreInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        // Clears the datastore
        runBlocking { context.store.edit { it.clear() } }
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testBoolean() = runBlocking {
        val pref = booleanPref(context.store, "testBoolean", false)
        assertEquals(pref.get(), false)
        pref.set(true)
        assertEquals(pref.get(), true)
        pref.set(false)
        assertEquals(pref.getBlocking(), false)
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testInt() = runBlocking {
        val pref = intPref(context.store, "testInt", 0)
        assertEquals(pref.get(), 0)
        pref.set(1)
        assertEquals(pref.get(), 1)
        pref.set(2)
        assertEquals(pref.getBlocking(), 2)
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testFloat() = runBlocking {
        val pref = floatPref(context.store, "testFloat", 0f)
        assertEquals(pref.get(), 0f)
        pref.set(1f)
        assertEquals(pref.get(), 1f)
        pref.set(2f)
        assertEquals(pref.getBlocking(), 2f)
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testDouble() = runBlocking {
        val pref = doublePref(context.store, "testDouble", 0.0)
        assertEquals(pref.get(), 0.0)
        pref.set(1.0)
        assertEquals(pref.get(), 1.0)
        pref.set(2.0)
        assertEquals(pref.getBlocking(), 2.0)
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testString() = runBlocking {
        val pref = stringPref(context.store, "testString", "42")
        assertEquals(pref.get(), "42")
        pref.set("Don't Panic")
        assertEquals(pref.get(), "Don't Panic")
        pref.set("Mostly Harmless")
        assertEquals(pref.getBlocking(), "Mostly Harmless")
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testStringSet() = runBlocking {
        val pref = stringSetPref(context.store, "testStringSet", setOf("42", "Don't Panic", "Mostly Harmless"))
        assertEquals(pref.get(), setOf("42", "Don't Panic", "Mostly Harmless"))
        pref.set(setOf("Don't Panic", "Mostly Harmless", "42"))
        assertEquals(pref.get(), setOf("Don't Panic", "Mostly Harmless", "42"))
        pref.set(setOf("Mostly Harmless", "42", "Don't Panic"))
        assertEquals(pref.getBlocking(), setOf("Mostly Harmless", "42", "Don't Panic"))
    }

    @OptIn(DangerousApi::class)
    @Test
    fun testGenPref() = runBlocking {
        val transform = object : Transform<Float> {
            override fun transform(t: Float): String = t.toString()
            override fun transform(t: String): Float = t.toFloat()
        }
        val pref = genPref(context.store, "genPref", 0f, transform)
        assertEquals(pref.get(), 0f)
        pref.set(1f)
        assertEquals(pref.get(), 1f)
        pref.set(2f)
        assertEquals(pref.getBlocking(), 2f)
    }
}
