package com.github.rumboalla.kryptostore.keystore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.rumboalla.kryptostore.createGsonTransform
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "test")

data class TestData(val one: String, val two: Double)

@RunWith(AndroidJUnit4::class)
class KryptoStoreGsonInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val gson = Gson()

    init {
        // Clears the datastore
        runBlocking { context.store.edit { it.clear() } }
    }

    @Test
    fun testKeystorePrefData() = runBlocking {
        val data = TestData("Don't Panic", 42.0)
        val data2 = TestData("Mostly Harmless", 43.0)

        val pref = encryptedKeystorePref(context.store, "testEncryptedPrefData", data, createGsonTransform(gson))
        pref.get().let {
            assertEquals(it.one, data.one)
            assertEquals(it.two, data.two)
        }
        pref.set(data2)
        pref.get().let {
            assertEquals(it.one, data2.one)
            assertEquals(it.two, data2.two)
        }
    }

    @Test
    fun testKeystorePrefDataList() = runBlocking {
        val data = listOf(TestData("Don't Panic", 42.0), TestData("Mostly Harmless", 43.0))
        val data2 = listOf(TestData("Mostly Harmless", 43.0), TestData("Don't Panic", 42.0))

        val pref = encryptedKeystorePref(context.store, "testEncryptedPrefDataList", data, createGsonTransform(gson))
        assertEquals(pref.get(), data)
        pref.set(data2)
        assertEquals(pref.get(), data2)
    }

    @Test
    fun testKeystorePrefBoolean() = runBlocking {
        val boolean = encryptedKeystorePref(context.store, "testEncryptedPrefBoolean", false, createGsonTransform(gson))
        assertEquals(boolean.get(), false)
        boolean.set(true)
        assertEquals(boolean.get(), true)
        boolean.set(false)
        assertEquals(boolean.get(), false)
    }

    @Test
    fun testKeystorePrefInt() = runBlocking {
        val int = encryptedKeystorePref(context.store, "testEncryptedPrefInt", 0, createGsonTransform(gson))
        assertEquals(int.get(), 0)
        int.set(1)
        assertEquals(int.get(), 1)
        int.set(0)
        assertEquals(int.get(), 0)
    }

    @Test
    fun testKeystorePrefFloat() = runBlocking {
        val float = encryptedKeystorePref(context.store, "testEncryptedPrefFloat", 0f, createGsonTransform(gson))
        assertEquals(float.get(), 0f)
        float.set(1f)
        assertEquals(float.get(), 1f)
        float.set(0f)
        assertEquals(float.get(), 0f)
    }

    @Test
    fun testKeystorePrefDouble() = runBlocking {
        val double = encryptedKeystorePref(context.store, "testEncryptedPrefDouble", 0.0, createGsonTransform(gson))
        assertEquals(double.get(), 0.0)
        double.set(1.0)
        assertEquals(double.get(), 1.0)
        double.set(0.0)
        assertEquals(double.get(), 0.0)
    }

    @Test
    fun testKeystorePrefString() = runBlocking {
        val string = encryptedKeystorePref(context.store, "testEncryptedPrefString", "Don't Panic", createGsonTransform(gson))
        assertEquals(string.get(), "Don't Panic")
        string.set("Mostly Harmless")
        assertEquals(string.get(), "Mostly Harmless")
        string.set("Don't Panic")
        assertEquals(string.get(), "Don't Panic")
    }

    @Test
    fun testKeystorePrefStringList() = runBlocking {
        val data = listOf("Mostly Harmless", "Don't panic")
        val data2 = listOf("Don't Panic", "Mostly Harmless")
        val stringList = encryptedKeystorePref(context.store, "testEncryptedPrefStringList", data, createGsonTransform(gson))
        assertEquals(stringList.get(), data)
        stringList.set(data2)
        assertEquals(stringList.get(), data2)
        stringList.set(data)
        assertEquals(stringList.get(), data)
    }

}
