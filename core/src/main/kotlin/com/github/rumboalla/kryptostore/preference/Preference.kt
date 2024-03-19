package com.github.rumboalla.kryptostore.preference

import com.github.rumboalla.kryptostore.annotation.DangerousApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking


interface Preference<T> {

    /**
     * Gets the value of a preference.
     *
     * @return  {@T}  Value of the preference.
     */
    suspend fun get(): T

    /**
     * Sets the value of a preference.
     *
     * @param   v   {@T}   Value {@T} to set in the preference.
     */
    suspend fun set(v: T)

    /**
     * Returns a flow of the preference.
     *
     * @return  {@Flow<T>}  Flow of the preference.
     */
    fun flow(): Flow<T>

    /**
     * Gets the value of the preference. This call blocks until the preference is retrieved.
     *
     * @return  {@T}  Value of the preference.
     */
    @DangerousApi
    fun getBlocking() = runBlocking { get() }
}
