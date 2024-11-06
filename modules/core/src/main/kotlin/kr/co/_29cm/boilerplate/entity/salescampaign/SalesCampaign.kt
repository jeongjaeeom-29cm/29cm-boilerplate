package kr.co._29cm.boilerplate.entity.salescampaign

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import jakarta.persistence.Basic
import jakarta.persistence.CascadeType
import jakarta.persistence.Convert
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Version
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus
import kr.co._29cm.boilerplate.entity.BaseMutableEntity
import kr.co._29cm.boilerplate.support.audit.captureChanges
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import org.hibernate.type.YesNoConverter
import org.springframework.util.Assert
import java.time.ZonedDateTime

@SoftDelete(
    strategy = SoftDeleteType.DELETED,
    converter = YesNoConverter::class,
)
@Entity
class SalesCampaign(
    id: Long = 0L,
    name: String,
    status: SalesCampaignStatus,
    period: DateTimePeriod,
    enabled: Boolean,
    histories: MutableList<SalesCampaignHistory>,
): BaseMutableEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = id

    @Version
    var version: Int = 0
        private set

    var name: String = name
        private set

    @Enumerated(EnumType.STRING)
    var status: SalesCampaignStatus = status
        private set

    @Embedded
    var period: DateTimePeriod = period
        private set

    @Basic
    @Convert(converter = YesNoConverter::class)
    var enabled: Boolean = enabled

    @OneToMany(mappedBy = "salesCampaign", cascade = [CascadeType.ALL], orphanRemoval = true)
    val histories: MutableList<SalesCampaignHistory> = histories

    fun modify(
        name: String,
        period: DateTimePeriod,
    ) {
        val changes = captureChanges(
            this::name,
            this::period,
        ) {
            this.name = name
            this.period = period
        }

        addHistory(description = "세일즈캠페인 수정", changes = changes)
    }

    fun enable() {
        val changes = captureChanges(
            this::enabled,
        ) {
            this.enabled = true
        }

        addHistory(description = "세일즈캠페인 활성화", changes = changes)
    }

    fun disable() {
        val changes = captureChanges(
            this::enabled,
        ) {
            this.enabled = false
        }

        addHistory(description = "세일즈캠페인 비활성화", changes = changes)
    }

    fun validateDeletable() {
        Assert.state(SalesCampaignStatus.DELETABLE_STATUSES.contains(this.status)) { "삭제 가능한 상태가 아닙니다." }
    }

    private fun addHistory(
        description: String,
        changes: ObjectNode = JsonNodeFactory.instance.objectNode(),
    ) {
        this.histories.add(
            SalesCampaignHistory.create(
                salesCampaign = this,
                description = description,
                changes = changes
            )
        )
    }

    companion object {
        fun create(
            name: String,
            period: DateTimePeriod,
        ): SalesCampaign {
            val now = ZonedDateTime.now()
            Assert.isTrue(period.startAt.isAfter(now)) { "시작 시간은 현재 시간 이후여야 합니다." }

            return SalesCampaign(
                name = name,
                status = SalesCampaignStatus.READY,
                period = period,
                enabled = true,
                histories = mutableListOf()
            ).apply {
                addHistory(description = "세일즈캠페인 생성")
            }
        }
    }
}