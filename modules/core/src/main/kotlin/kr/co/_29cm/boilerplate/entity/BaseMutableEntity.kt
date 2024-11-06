package kr.co._29cm.boilerplate.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseMutableEntity: BaseEntity() {
    @Column(nullable = false, updatable = true)
    protected var updatedAt: ZonedDateTime = ZonedDateTime.now()

    @LastModifiedBy
    @Column(nullable = true, updatable = true)
    protected var updatedBy: String? = null

    override fun onPrePersist() {
        val now = ZonedDateTime.now()
        this.createdAt = now
        this.updatedAt = now
    }

    @PreUpdate
    protected open fun onPreUpdate() {
        this.updatedAt = ZonedDateTime.now()
    }
}