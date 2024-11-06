package kr.co._29cm.boilerplate.transaction

import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionOperations

@Component
class SalesCampaignTransactions(
    private val transactionOperations: TransactionOperations,
    private val readTransactionOperations: TransactionOperations,
) : Transactions {
    @Suppress("UNCHECKED_CAST")
    override fun <T> inTransaction(action: () -> T): T {
        return transactionOperations.execute { action() } as T
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> inReadTransaction(action: () -> T): T {
        return readTransactionOperations.execute { action() } as T
    }
}