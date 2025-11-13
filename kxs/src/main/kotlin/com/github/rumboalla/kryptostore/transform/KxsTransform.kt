package com.github.rumboalla.kryptostore.transform

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.lang.reflect.Type


class KxsTransform<T>(private val type: Type): Transform<T> {

    override fun transform(t: T): String {
        if (t == null) return "{}"
        return Json.encodeToString(serializer(type), t)
    }

    @Suppress("UNCHECKED_CAST")
    override fun transform(t: String): T {
        if (t == "{}") return null as T
        return Json.decodeFromString(serializer(type), t) as T
    }

}
