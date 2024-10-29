package com.github.rumboalla.kryptostore.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.rumboalla.kryptostore.preference.Preference
import com.github.rumboalla.kryptostore.preference.stringPref
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "test")

@RunWith(AndroidJUnit4::class)
class KryptoStoreComposeInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        // Clears the datastore
        runBlocking { context.store.edit { it.clear() } }
    }

    @Test
    fun testCompose() = runBlocking {
        val pref = stringPref(context.store, "testString", "42")
        composeTestRule.setContent {
            TestComposable(pref)
        }
    }

    @Composable
    fun TestComposable(pref: Preference<String>) {
        pref.collectAsStateWithLifecycle().value.let {
            println("Value: $it")
        }
    }

}
