package kr.co._29cm.boilerplate.support.datetime

import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime

fun ChronoZonedDateTime<*>.isBeforeOrEqual(other: ChronoZonedDateTime<*>): Boolean {
    return this.isBefore(other) || this.isEqual(other)
}

fun ChronoZonedDateTime<*>.isAfterOrEqual(other: ChronoZonedDateTime<*>): Boolean {
    return this.isAfter(other) || this.isEqual(other)
}

fun String.toZonedDateTime(): ZonedDateTime {
    return ZonedDateTime.parse(this)
}