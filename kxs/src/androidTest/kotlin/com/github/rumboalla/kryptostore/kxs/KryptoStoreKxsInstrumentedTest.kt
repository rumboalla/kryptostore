package com.github.rumboalla.kryptostore.kxs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.rumboalla.kryptostore.kxsPref
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.junit.Test
import org.junit.runner.RunWith


private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "test")

@Serializable
data class TestData(val one: String, val two: Double)

@RunWith(AndroidJUnit4::class)
class KryptoStoreKxsInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        // Clears the datastore
        runBlocking { context.store.edit { it.clear() } }
    }

    @Test
    fun testKxsPrefData() = runBlocking {
        val data = TestData("Don't Panic", 42.0)
        val data2 = TestData("Mostly Harmless", 43.0)

        val pref = kxsPref(context.store, "testGsonPrefData", data)
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
    fun testKxsPrefDataList() = runBlocking {
        val data = listOf(TestData("Don't Panic", 42.0), TestData("Mostly Harmless", 43.0))
        val data2 = listOf(TestData("Mostly Harmless", 43.0), TestData("Don't Panic", 42.0))

        val pref = kxsPref(context.store, "testGsonPrefDataList", data)
        assertEquals(pref.get(), data)
        pref.set(data2)
        assertEquals(pref.get(), data2)
    }

    @Test
    fun testKxsPrefBoolean() = runBlocking {
        val boolean = kxsPref(context.store, "testGsonPrefBoolean", false)
        assertEquals(boolean.get(), false)
        boolean.set(true)
        assertEquals(boolean.get(), true)
        boolean.set(false)
        assertEquals(boolean.get(), false)
    }

    @Test
    fun testKxsPrefInt() = runBlocking {
        val int = kxsPref(context.store, "testGsonPrefInt", 0)
        assertEquals(int.get(), 0)
        int.set(1)
        assertEquals(int.get(), 1)
        int.set(0)
        assertEquals(int.get(), 0)
    }

    @Test
    fun testKxsPrefFloat() = runBlocking {
        val float = kxsPref(context.store, "testGsonPrefFloat", 0f)
        assertEquals(float.get(), 0f)
        float.set(1f)
        assertEquals(float.get(), 1f)
        float.set(0f)
        assertEquals(float.get(), 0f)
    }

    @Test
    fun testKxsPrefDouble() = runBlocking {
        val double = kxsPref(context.store, "testGsonPrefDouble", 0.0)
        assertEquals(double.get(), 0.0)
        double.set(1.0)
        assertEquals(double.get(), 1.0)
        double.set(0.0)
        assertEquals(double.get(), 0.0)
    }

    @Test
    fun testKxsPrefString() = runBlocking {
        val string = kxsPref(context.store, "testGsonPrefString", "Don't Panic")
        assertEquals(string.get(), "Don't Panic")
        string.set("Mostly Harmless")
        assertEquals(string.get(), "Mostly Harmless")
        string.set("Don't Panic")
        assertEquals(string.get(), "Don't Panic")
    }

    @Test
    fun testKxsPrefStringList() = runBlocking {
        val data = listOf("Mostly Harmless", "Don't panic")
        val data2 = listOf("Don't panic", "Mostly Harmless")
        val stringList = kxsPref(context.store, "testGsonPrefStringList", data)
        assertEquals(stringList.get(), data)
        stringList.set(data2)
        assertEquals(stringList.get(), data2)
        stringList.set(data)
        assertEquals(stringList.get(), data)
    }

}
