package com.github.rumboalla.kryptostore.annotation


@RequiresOptIn("This is a dangerous call. Please make sure you understand how it works.")
@Retention(AnnotationRetention.BINARY)
annotation class DangerousApi
