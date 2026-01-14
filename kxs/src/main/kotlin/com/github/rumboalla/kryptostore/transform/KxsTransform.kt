package com.github.rumboalla.kryptostore.transform

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.lang.reflect.Type


class KxsTransform<T>(
    private val type: Type,
    private val json: Json,
    private val serializer: KSerializer<T>?
): Transform<T> {

    override fun transform(t: T): String {
        if (t == null) return "{}"
        return if (serializer != null) json.encodeToString(serializer, t) else json.encodeToString(serializer(type), t)
    }

    @Suppress("UNCHECKED_CAST")
    override fun transform(t: String): T {
        if (t == "{}") return null as T
        return if (serializer != null) json.decodeFromString(serializer, t) else json.decodeFromString(serializer(type), t) as T
    }

}
