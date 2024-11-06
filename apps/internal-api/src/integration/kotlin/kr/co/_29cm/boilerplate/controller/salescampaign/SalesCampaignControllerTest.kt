package kr.co._29cm.boilerplate.controller.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus
import kr.co._29cm.boilerplate.support.datetime.toZonedDateTime
import kr.co._29cm.boilerplate.usecase.salescampaign.CreateSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.DeleteSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.EnableSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.GetSalesCampaignUseCase
import kr.co._29cm.boilerplate.usecase.salescampaign.ModifySalesCampaignUseCase
import kr.co._29cm.common.contract.OffsetPage
import kr.co._29cm.common.contract.OffsetPagination
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [SalesCampaignController::class])
class SalesCampaignControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var getSalesCampaignUseCase: GetSalesCampaignUseCase

    @MockBean
    lateinit var createSalesCampaignUseCase: CreateSalesCampaignUseCase

    @MockBean
    lateinit var modifySalesCampaignUseCase: ModifySalesCampaignUseCase

    @MockBean
    lateinit var enableSalesCampaignUseCase: EnableSalesCampaignUseCase

    @MockBean
    lateinit var deleteSalesCampaignUseCase: DeleteSalesCampaignUseCase

    @DisplayName("세일즈캠페인 단건 조회")
    @Test
    fun get() {
        given(getSalesCampaignUseCase.getSalesCampaign(any()))
            .willReturn(
                GetSalesCampaignUseCase.GetSalesCampaign(
                    salesCampaignId = 1,
                    name = "캠페인",
                    period = DateTimePeriod(
                        startAt = "2021-01-01T00:00:00+09:00".toZonedDateTime(),
                        endAt = "2021-01-02T00:00:00+09:00".toZonedDateTime()
                    ),
                    enabled = true,
                    status = SalesCampaignStatus.READY
                )
            )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/internal/v4/sales-campaigns/{salesCampaignId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.data.salesCampaignId").value(1),
            jsonPath("$.data.name").value("캠페인"),
            jsonPath("$.data.period.startAt").value("2021-01-01T00:00:00+09:00"),
            jsonPath("$.data.period.endAt").value("2021-01-02T00:00:00+09:00"),
            jsonPath("$.data.enabled").value(true),
            jsonPath("$.data.status").value("READY")
        )
    }

    @DisplayName("세일즈캠페인 페이지 조회")
    @Test
    fun page() {
        given(getSalesCampaignUseCase.getSalesCampaignPage(any()))
            .willReturn(
                OffsetPage(
                    list = listOf(
                        GetSalesCampaignUseCase.GetSalesCampaign(
                            salesCampaignId = 1,
                            name = "캠페인",
                            period = DateTimePeriod(
                                startAt = "2021-01-01T00:00:00+09:00".toZonedDateTime(),
                                endAt = "2021-01-02T00:00:00+09:00".toZonedDateTime()
                            ),
                            enabled = true,
                            status = SalesCampaignStatus.READY
                        )
                    ),
                    pagination = OffsetPagination.of(
                        page = 1,
                        size = 10,
                        totalCount = 1
                    )
                )
            )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/internal/v4/sales-campaigns:page")
                .queryParam("page", "1")
                .queryParam("size", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.data.pagination.page").value(1),
            jsonPath("$.data.pagination.size").value(10),
            jsonPath("$.data.pagination.totalCount").value(1),
            jsonPath("$.data.list[0].salesCampaignId").value(1),
            jsonPath("$.data.list[0].name").value("캠페인"),
            jsonPath("$.data.list[0].period.startAt").value("2021-01-01T00:00:00+09:00"),
            jsonPath("$.data.list[0].period.endAt").value("2021-01-02T00:00:00+09:00"),
            jsonPath("$.data.list[0].enabled").value(true),
            jsonPath("$.data.list[0].status").value("READY")
        )
    }

    @DisplayName("세일즈캠페인 생성")
    @Test
    fun create() {
        given(createSalesCampaignUseCase.createSalesCampaign(any()))
            .willReturn(
                CreateSalesCampaignUseCase.CreateResult(
                    salesCampaignId = 1
                )
            )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/internal/v4/sales-campaigns:create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "캠페인",
                        "period": {
                            "startAt": "2021-01-01T00:00:00+09:00",
                            "endAt": "2021-01-02T00:00:00+09:00"
                        }
                    }
                    """.trimIndent()
                )
        ).andExpectAll(
            status().isOk,
            jsonPath("$.data.salesCampaignId").value(1)
        )
    }

    @DisplayName("세일즈캠페인 수정")
    @Test
    fun modify() {
        val salesCampaignId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.post("/internal/v4/sales-campaigns/{salesCampaignId}:modify", salesCampaignId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "캠페인",
                        "period": {
                            "startAt": "2021-01-01T00:00:00+09:00",
                            "endAt": "2021-01-02T00:00:00+09:00"
                        }
                    }
                    """.trimIndent()
                )
        ).andExpectAll(
            status().isOk,
        )

        verify(modifySalesCampaignUseCase).modifySalesCampaign(any())
    }

    @DisplayName("세일즈캠페인 활성화")
    @Test
    fun enable() {
        val salesCampaignId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.post("/internal/v4/sales-campaigns/{salesCampaignId}:enable", salesCampaignId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk,
        )

        verify(enableSalesCampaignUseCase).enableSalesCampaign(any())
    }

    @DisplayName("세일즈캠페인 비활성화")
    @Test
    fun disable() {
        val salesCampaignId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.post("/internal/v4/sales-campaigns/{salesCampaignId}:disable", salesCampaignId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk,
        )

        verify(enableSalesCampaignUseCase).disableSalesCampaign(any())
    }

    @DisplayName("세일즈캠페인 삭제")
    @Test
    fun delete() {
        val salesCampaignId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.post("/internal/v4/sales-campaigns/{salesCampaignId}:delete", salesCampaignId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk,
        )

        verify(deleteSalesCampaignUseCase).deleteSalesCampaign(any())
    }
}