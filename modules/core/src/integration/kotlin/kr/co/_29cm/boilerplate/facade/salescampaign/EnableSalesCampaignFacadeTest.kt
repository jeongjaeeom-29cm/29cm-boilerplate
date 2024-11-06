package kr.co._29cm.boilerplate.facade.salescampaign

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.DataSetFormat
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.core.api.exporter.ExportDataSet
import kr.co._29cm.boilerplate.AbstractIntegrationTest
import kr.co._29cm.boilerplate.usecase.salescampaign.EnableSalesCampaignUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class EnableSalesCampaignFacadeTest : AbstractIntegrationTest() {
    @Autowired
    lateinit var sut: EnableSalesCampaignFacade

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_disabled.yml",
        ]
    )
    @ExpectedDataSet(
        value = [
            "datasets/salescampaign/expect_sales_campaign_enabled.yml",
        ]
    )
    @ExportDataSet(format = DataSetFormat.YML, outputName = "build/exported/yml/sales_campaign_enabled.yml")
    @DisplayName("세일즈캠페인을 활성화 할 수 있다.")
    @Test
    fun enableSalesCampaign() {
        val salesCampaignId = 1L

        sut.enableSalesCampaign(
            EnableSalesCampaignUseCase.EnableRequest(
                salesCampaignId = salesCampaignId,
            )
        )
    }

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_ready.yml",
        ]
    )
    @ExpectedDataSet(
        value = [
            "datasets/salescampaign/expect_sales_campaign_disabled.yml",
        ]
    )
    @ExportDataSet(format = DataSetFormat.YML, outputName = "build/exported/yml/sales_campaign_disabled.yml")
    @DisplayName("세일즈캠페인을 비활성화 할 수 있다.")
    @Test
    fun disableSalesCampaign() {
        val salesCampaignId = 1L

        sut.disableSalesCampaign(
            EnableSalesCampaignUseCase.DisableRequest(
                salesCampaignId = salesCampaignId,
            )
        )
    }
}