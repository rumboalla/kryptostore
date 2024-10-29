package com.github.rumboalla.kryptostore.transform

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.lang.reflect.Type


class KxsTransform<T : Any>(private val type: Type): Transform<T> {

    override fun transform(t: T): String = Json.encodeToString(serializer(type), t)

    override fun transform(t: String): T = Json.decodeFromString(serializer(type), t) as T

}
