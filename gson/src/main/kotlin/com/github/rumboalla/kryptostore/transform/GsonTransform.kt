package com.github.rumboalla.kryptostore.transform

import com.google.gson.Gson
import java.lang.reflect.Type


class GsonTransform<T>(private val gson: Gson, private val type: Type): Transform<T> {
    override fun transform(t: T): String = gson.toJson(t)
    override fun transform(t: String): T = gson.fromJson(t, type)
}
