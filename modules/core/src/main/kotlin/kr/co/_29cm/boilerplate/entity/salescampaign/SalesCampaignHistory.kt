package kr.co._29cm.boilerplate.entity.salescampaign

import com.fasterxml.jackson.databind.node.ObjectNode
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import kr.co._29cm.boilerplate.entity.BaseEntity
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import org.hibernate.annotations.Type
import org.hibernate.type.YesNoConverter


@SoftDelete(
    strategy = SoftDeleteType.DELETED,
    converter = YesNoConverter::class,
)
@Entity
class SalesCampaignHistory(
    id: Long = 0L,
    salesCampaign: SalesCampaign,
    description: String,
    changes: ObjectNode,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = id

    @ManyToOne
    @JoinColumn(name = "sales_campaign_id")
    val salesCampaign: SalesCampaign = salesCampaign

    val description: String = description

    @Type(JsonType::class)
    val changes: ObjectNode = changes

    companion object {
        internal fun create(
            salesCampaign: SalesCampaign,
            description: String,
            changes: ObjectNode,
        ) = SalesCampaignHistory(
            salesCampaign = salesCampaign,
            description = description,
            changes = changes
        )
    }
}