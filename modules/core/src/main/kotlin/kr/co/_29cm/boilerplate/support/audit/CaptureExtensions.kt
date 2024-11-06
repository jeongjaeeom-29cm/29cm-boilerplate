package kr.co._29cm.boilerplate.support.audit

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KProperty

data class Change<T>(
    val name: String,
    val value: Value<T>,
) {
    data class Value<T>(
        val old: T,
        val new: T,
    ) {
        @JsonIgnore
        fun isChange() = old != new
    }
}

typealias Assembler<T> = (String, T, T) -> Change<T>

typealias Capture = () -> Change<*>

fun <T> KProperty<T>.capture(name: String, assembler: Assembler<T>): Capture {
    val oldValue = this.getter.call()
    return { assembler(name, oldValue, this.getter.call()) }
}

fun <T> KProperty<T>.capture() = this.capture(this.name) { name, old, new -> Change(name, Change.Value(old, new)) }

@OptIn(ExperimentalContracts::class)
inline fun captureChanges(vararg properties: KProperty<*>, block: () -> Unit): ObjectNode {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    val captures = properties.map { it.capture() }

    block()

    return captures.map { it() }
        .filter { it.value.isChange() }
        .fold(JsonNodeFactory.instance.objectNode()) { acc, captured ->
            acc.putPOJO(captured.name, captured.value)
        }
}
