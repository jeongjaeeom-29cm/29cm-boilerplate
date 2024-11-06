package kr.co._29cm.boilerplate.facade.salescampaign

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.DataSetFormat
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.core.api.exporter.ExportDataSet
import kr.co._29cm.boilerplate.AbstractIntegrationTest
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.usecase.salescampaign.ModifySalesCampaignUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.ZonedDateTime

class ModifySalesCampaignFacadeTest : AbstractIntegrationTest() {
    @Autowired
    lateinit var sut: ModifySalesCampaignFacade

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_ready.yml",
        ]
    )
    @ExpectedDataSet(
        value = [
            "datasets/salescampaign/expect_sales_campaign_modified.yml",
        ]
    )
    @ExportDataSet(format = DataSetFormat.YML, outputName = "build/exported/yml/sales_campaign_modified.yml")
    @DisplayName("세일즈캠페인을 수정 할 수 있다.")
    @Test
    fun modifySalesCampaign() {
        val salesCampaignId = 1L
        val name = "수요입점회 W26"
        val period = DateTimePeriod(
            startAt = ZonedDateTime.parse("2024-10-08T00:00:00+00:00"),
            endAt = ZonedDateTime.parse("2024-10-10T00:00:00+00:00"),
        )

        sut.modifySalesCampaign(
            ModifySalesCampaignUseCase.ModifyRequest(
                salesCampaignId = salesCampaignId,
                name = name,
                period = period,
            )
        )
    }
}