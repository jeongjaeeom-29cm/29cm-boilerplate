package kr.co._29cm.boilerplate.facade.salescampaign

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.DataSetFormat
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.core.api.exporter.ExportDataSet
import kr.co._29cm.boilerplate.AbstractIntegrationTest
import kr.co._29cm.boilerplate.usecase.salescampaign.DeleteSalesCampaignUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class DeleteSalesCampaignFacadeTest : AbstractIntegrationTest() {
    @Autowired
    lateinit var sut: DeleteSalesCampaignFacade

    @DataSet(
        value = [
            "datasets/salescampaign/given_sales_campaign_ready.yml",
        ]
    )
    @ExpectedDataSet(
        value = [
            "datasets/salescampaign/expect_sales_campaign_deleted.yml",
        ]
    )
    @ExportDataSet(format = DataSetFormat.YML, outputName = "build/exported/yml/sales_campaign_deleted.yml")
    @DisplayName("세일즈캠페인을 삭제 할 수 있다.")
    @Test
    fun deleteSalesCampaign() {
        val request = DeleteSalesCampaignUseCase.DeleteRequest(
            salesCampaignId = 1,
        )

        sut.deleteSalesCampaign(request)
    }
}