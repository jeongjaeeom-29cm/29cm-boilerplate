package kr.co._29cm.boilerplate.config.jpa

import kr.co._29cm.boilerplate.entity.EntityModule
import kr.co._29cm.boilerplate.repository.PersistenceModule
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate


@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = [PersistenceModule::class])
@EntityScan(basePackageClasses = [EntityModule::class])
class JpaConfig(
    private val transactionManager: PlatformTransactionManager
) {
    @Bean
    fun transactionOperations(): TransactionTemplate {
        return TransactionTemplate(transactionManager)
    }

    @Bean
    fun readTransactionOperations(): TransactionTemplate {
        return TransactionTemplate(transactionManager).apply {
            isReadOnly = true
        }
    }
}