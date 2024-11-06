package kr.co._29cm.boilerplate.facade.salescampaign

import com.github.database.rider.core.api.dataset.DataSetFormat
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.core.api.exporter.ExportDataSet
import kr.co._29cm.boilerplate.AbstractIntegrationTest
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.usecase.salescampaign.CreateSalesCampaignUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.ZonedDateTime

class CreateSalesCampaignFacadeTest : AbstractIntegrationTest() {

    @Autowired
    lateinit var sut: CreateSalesCampaignFacade

    @ExpectedDataSet(
        value = [
            "datasets/salescampaign/expect_sales_campaign_created.yml",
        ]
    )
    @ExportDataSet(format = DataSetFormat.YML, outputName = "build/exported/yml/sales_campaign_created.yml")
    @DisplayName("세일즈캠페인을 생성 할 수 있다.")
    @Test
    fun createSalesCampaign() {
        val request = CreateSalesCampaignUseCase.CreateRequest(
            name = "수요입점회 W25",
            period = DateTimePeriod(
                startAt = ZonedDateTime.now().plusDays(1),
                endAt = ZonedDateTime.now().plusDays(2),
            )
        )

        val result = sut.createSalesCampaign(request)

        assertThat(result.salesCampaignId).isNotNull()
    }
}