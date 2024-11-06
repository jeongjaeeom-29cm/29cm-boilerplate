package kr.co._29cm.boilerplate.support.logging

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging

fun logger(name: String): KLogger = KotlinLogging.logger(name)

inline fun <reified T : Any> T.logger(): KLogger = logger(T::class.java.name)
