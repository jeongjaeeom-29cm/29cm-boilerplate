package kr.co._29cm.boilerplate.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseEntity {
    @Column(nullable = false, updatable = false)
    protected var createdAt: ZonedDateTime = ZonedDateTime.now()

    @CreatedBy
    @Column(nullable = true, updatable = false)
    protected var createdBy: String? = null

    @PrePersist
    protected open fun onPrePersist() {
        createdAt = ZonedDateTime.now()
    }
}