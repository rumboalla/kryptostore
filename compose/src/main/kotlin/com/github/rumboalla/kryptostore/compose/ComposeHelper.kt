package com.github.rumboalla.kryptostore.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.rumboalla.kryptostore.preference.Preference


@Composable
fun <T> Preference<T>.collectAsStateWithLifecycle(): State<T> {
    return this.flow().collectAsStateWithLifecycle(defaultValue())
}
