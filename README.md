# KryptoStore
KryptoStore is thin wrapper around Jetpack DataStore Preferences that provides useful features.

## Features
* Small
* Easily work with primitive preferences
* Serialization for complex objects
* Encryption

## Basic Usage
Import library: TODO
```kotlin
implementation("TODO")
```
Use preferences. Supported preferences: booleanPref, intPref, floatPref, doublePref, stringPref, stringSetPref.
```kotlin
val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")
val pref = booleanPref(context.store, "testBoolean", false)
val value = pref.get()
pref.set(true)
```

## Advanced Usage
Import library for serialization
```kotlin
implementation("TODO")
```
Create serialized preferences:
```kotlin
data class TestData(val one: String, val two: Double)
val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")
val pref = gsonPref(context.store, "testGsonPrefData", TestData("Don't Panic", 42.0), Gson())
val value = pref.get()
pref.set(TestData("Mostly Harmless", 43.0))
```

## Encryption
Import library for encryption
```kotlin
implementation("TODO")
```
Create encrypted preferences:
```kotlin
data class TestData(val one: String, val two: Double)
val Context.store: DataStore<Preferences> by preferencesDataStore(name = "prefs")
val pref = encryptedKeystorePref(context.store, "testEncryptedPrefData", TestData("Don't Panic", 42.0), createGsonTransform(gson))
val value = pref.get()
pref.set(TestData("Mostly Harmless", 43.0))
```

## TODO
* More serialization options: Moshi, kotlinx.serialization, etc...
* More encryption options.