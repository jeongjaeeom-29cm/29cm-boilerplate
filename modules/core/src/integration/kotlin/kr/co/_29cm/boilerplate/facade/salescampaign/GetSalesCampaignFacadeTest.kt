package kr.co._29cm.boilerplate.facade.salescampaign

import com.github.database.rider.core.api.dataset.DataSet
import kr.co._29cm.boilerplate.AbstractIntegrationTest
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus
import kr.co._29cm.boilerplate.usecase.salescampaign.GetSalesCampaignUseCase
import kr.co._29cm.common.contract.OffsetPageRequest
import kr.co._29cm.common.contract.OffsetPagination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class GetSalesCampaignFacadeTest : AbstractIntegrationTest() {
    @Autowired
    lateinit var sut: GetSalesCampaignFacade

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_set.yml",
        ]
    )
    @DisplayName("세일즈캠페인를 단건으로 조회 할 수 있다. (조회쿼리 : ID)")
    @Test
    fun getSalesCampaign() {
        val request = GetSalesCampaignUseCase.GetRequest(
            salesCampaignId = 6L,
        )

        val actual = sut.getSalesCampaign(request)

        assertThat(actual.salesCampaignId).isEqualTo(6L)
        assertThat(actual.name).isEqualTo("수요입점회 W30")
        assertThat(actual.status).isEqualTo(SalesCampaignStatus.IN_PROGRESS)
        assertThat(actual.period.startAt).isEqualTo("2024-07-24T00:00:00+00:00")
        assertThat(actual.period.endAt).isEqualTo("2024-07-25T00:00:00+00:00")
    }

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_set.yml",
        ]
    )
    @DisplayName("세일즈캠페인을 페이지 단위로 조회 할 수 있다.")
    @Test
    fun getSalesCampaignPage() {
        val request = GetSalesCampaignUseCase.GetRequestPage(
            pageRequest = OffsetPageRequest.of(1, 5),
        )

        val actual = sut.getSalesCampaignPage(request)

        with(actual) {
            assertThat(pagination).isEqualTo(
                OffsetPagination(
                    page = 1,
                    size = 5,
                    totalCount = 9,
                    totalPages = 2,
                    hasNext = true
                )
            )
            assertThat(list).hasSize(5)
            assertThat(list[0].salesCampaignId).isEqualTo(9L)
            assertThat(list[1].salesCampaignId).isEqualTo(8L)
            assertThat(list[2].salesCampaignId).isEqualTo(7L)
            assertThat(list[3].salesCampaignId).isEqualTo(6L)
            assertThat(list[4].salesCampaignId).isEqualTo(5L)
        }
    }
}