# KryptoStore [![](https://jitpack.io/v/rumboalla/kryptostore.svg)](https://jitpack.io/#rumboalla/kryptostore) [![](https://jitpack.io/v/rumboalla/kryptostore/month.svg)](https://jitpack.io/#rumboalla/kryptostore)
**KryptoStore** is a thin wrapper around **Jetpack DataStore Preferences** that provides useful features.

## Features
* Small
* Easily work with primitive preferences
* Serialization for complex objects
* Encryption

## Basic Usage
Add the Jitpack repository
```kotlin
maven { url = uri("https://www.jitpack.io" ) }
```
Import the library
```kotlin
implementation("com.github.rumboalla.kryptostore:core:0.1.1")
```
Use preferences
```kotlin
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.rumboalla.kryptostore.preference.booleanPref
import com.github.rumboalla.kryptostore.preference.doublePref
import com.github.rumboalla.kryptostore.preference.floatPref
import com.github.rumboalla.kryptostore.preference.intPref
import com.github.rumboalla.kryptostore.preference.stringPref
import com.github.rumboalla.kryptostore.preference.stringSetPref

private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")

class Prefs(context: Context) {
    val boolean = booleanPref(context.store, "boolean", true)
    val int = intPref(context.store, "int", 42)
    val float = floatPref(context.store, "float", 42f)
    val double = doublePref(context.store, "double", 42.0)
    val string = stringPref(context.store, "string", "Don't Panic")
    val stringSet = stringSetPref(context.store, "stringSet", setOf("Mostly Harmless", "Don't Panic"))
}

suspend fun doSomething(context: Context) {
    val prefs = Prefs(context)
    val boolean = prefs.boolean.get()
    prefs.boolean.set(!boolean)
    val int = prefs.int.get()
    prefs.int.set(int + 1)
    val float = prefs.float.get()
    prefs.float.set(float + 1f)
    val double = prefs.double.get()
    prefs.double.set(double + 1.0)
    val string = prefs.string.get()
    prefs.string.set("$string!")
    val stringSet = prefs.stringSet.get()
    prefs.stringSet.set(emptySet())
}
```

## Advanced Usage
Import the gson library for serialization
```kotlin
implementation("com.github.rumboalla.kryptostore:gson:0.1.1")
```
Use serialized preferences
```kotlin
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.rumboalla.kryptostore.gsonPref
import com.google.gson.Gson

private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")
private val gson = Gson()

data class Data(val key: String = "", val value: Double = 0.0)

class Prefs(context: Context) {
    val data = gsonPref(context.store, "data", Data(), gson)
}

suspend fun doSomething(context: Context) {
    val prefs = Prefs(context)
    val data = prefs.data.get()
    prefs.data.set(data.copy(key = "key", value = 42.0))
}
```

## Encryption
Import the library for encryption
```kotlin
implementation("com.github.rumboalla.kryptostore:keystore:0.1.1")
```
Use encrypted preferences
```kotlin
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.rumboalla.kryptostore.createGsonTransform
import com.google.gson.Gson

private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")
private val gson = Gson()

data class Data(val key: String = "", val value: Double = 0.0)

class Prefs(context: Context) {
    val data = encryptedKeystorePref(context.store, "data", Data(), createGsonTransform(gson))
}

suspend fun doSomething(context: Context) {
    val prefs = Prefs(context)
    val data = prefs.data.get()
    prefs.data.set(data.copy(key = "key", value = 42.0))
}
```

## Roadmap
* More serialization options: Moshi, kotlinx.serialization.
* More encryption options.

## License
Copyright © 2024 rumboalla.  
Licensed under the [MIT](https://github.com/rumboalla/kryptostore/blob/master/LICENSE) license.
