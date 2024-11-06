package kr.co._29cm.boilerplate.transaction

interface Transactions {
    fun <T> inTransaction(action: () -> T): T

    fun <T> inReadTransaction(action: () -> T): T

    companion object {
        val NOOP: Transactions = object : Transactions {
            override fun <T> inTransaction(action: () -> T): T = action()

            override fun <T> inReadTransaction(action: () -> T): T = action()
        }
    }
}