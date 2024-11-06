package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.exception.salescampaign.NotFoundSalesCampaignException
import kr.co._29cm.boilerplate.entity.salescampaign.QSalesCampaign.salesCampaign
import kr.co._29cm.boilerplate.entity.salescampaign.SalesCampaign
import kr.co._29cm.boilerplate.support.querydsl.fetchPage
import kr.co._29cm.boilerplate.transaction.Transactions
import kr.co._29cm.common.contract.OffsetPage
import kr.co._29cm.common.contract.OffsetPageRequest
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

@Service
class SalesCampaignReader(
    private val transactions: Transactions
) : QuerydslRepositorySupport(SalesCampaign::class.java) {

    fun getSalesCampaignPage(pageRequest: OffsetPageRequest): OffsetPage<SalesCampaignDto> {
        return transactions.inReadTransaction {
            from(salesCampaign)
                .select(SalesCampaignDtoProjection)
                .orderBy(salesCampaign.id.desc())
                .fetchPage(pageRequest)
        }
    }

    fun getSalesCampaign(id: Long): SalesCampaignDto {
        return transactions.inReadTransaction {
            from(salesCampaign)
                .select(SalesCampaignDtoProjection)
                .where(salesCampaign.id.eq(id))
                .fetchFirst() ?: throw NotFoundSalesCampaignException(id)
        }
    }
}